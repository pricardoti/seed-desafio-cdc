package br.com.pricardoti.casacodigo.controllers;

import br.com.pricardoti.casacodigo.autor.Autor;
import br.com.pricardoti.casacodigo.autor.AutorController;
import br.com.pricardoti.casacodigo.autor.AutorRepository;
import br.com.pricardoti.casacodigo.autor.SalvarAutorRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Test Autor Controller")
@ExtendWith(SpringExtension.class)
@WebMvcTest(AutorController.class)
public class AutorControllerTest {

    private final MockMvc mockMvc;
    private final String URL_BASE = "/autores";
    private final ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private AutorRepository autorRepository;

    @Autowired
    public AutorControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Nested
    @DisplayName("Consultar Autor")
    class ConsultarAutor {
        @Test
        @DisplayName("Sucesso")
        public void consultarAutor_sucesso() {
        }
    }

    @Nested
    @DisplayName("Salvar Autor")
    class SalvarAutor {

        @Test
        @DisplayName("Sucesso")
        public void salvarAutor_sucesso() throws Exception {
            final String codigo = UUID.randomUUID().toString();
            final String nome = "Robert C. Martin (Uncle Bob)";
            final String email = "unclebob@cleancoder.com";
            final String descricao = "Robert C. Martin (Uncle Bob) has been a programmer since 1970, is an international firm of highly experienced software developers and managers who specialize in helping companies get their projects done.";

            when(autorRepository.findByEmail(anyString())).thenReturn(Optional.empty());
            when(autorRepository.save(any(Autor.class))).thenReturn(new Autor(codigo, nome, email, descricao, LocalDateTime.now()));

            SalvarAutorRequest autorRequest = new SalvarAutorRequest(nome, email, descricao);
            mockMvc.perform(
                    post(URL_BASE)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(autorRequest)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.codigo", is(codigo)));
        }

        @Test
        @DisplayName("Falha todos os campos")
        public void salvarAutor_todosCamposInvalidos_badRequest() throws Exception {
            SalvarAutorRequest autorRequest = new SalvarAutorRequest();
            mockMvc.perform(
                    post(URL_BASE)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(autorRequest)))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                    .andExpect(result -> assertTrue(result.getResolvedException().getMessage().startsWith("Validation failed for argument")))
                    .andExpect(jsonPath("$.statusCode", is(HttpStatus.BAD_REQUEST.value())))
                    .andExpect(jsonPath("$.message", equalTo("Method Argument Not Valid Exception")))
                    .andExpect(jsonPath("$.description", equalTo("Field Validation Error")))
                    .andExpect(jsonPath("$.timestamp", notNullValue()))
                    .andExpect(jsonPath("$.errors", notNullValue()))
                    .andExpect(jsonPath("$.errors", hasSize(3)));
        }

        @Test
        @DisplayName("Falha Nome")
        public void salvarAutor_nomeNaoPreenchido_badRequest() throws Exception {
            final String nome = "Robert C. Martin (Uncle Bob)";
            final String email = "unclebob@cleancoder.com";
            final String descricao = "Robert C. Martin (Uncle Bob) has been a programmer since 1970, is an international firm of highly experienced software developers and managers who specialize in helping companies get their projects done.";

            SalvarAutorRequest autorRequest = new SalvarAutorRequest(null, email, descricao);
            executarMockMvcfail(autorRequest, "nome", "O campo 'nome' é obrigatório.");
        }

        @Test
        @DisplayName("Falha Email Não Preenchido")
        public void salvarAutor_emailNaoPreenchido_badRequest() throws Exception {
            final String nome = "Robert C. Martin (Uncle Bob)";
            final String descricao = "Robert C. Martin (Uncle Bob) has been a programmer since 1970, is an international firm of highly experienced software developers and managers who specialize in helping companies get their projects done.";

            SalvarAutorRequest autorRequest = new SalvarAutorRequest(nome, null, descricao);
            executarMockMvcfail(autorRequest, "email", "O campo 'e-mail' é obrigatório.");
        }

        @Test
        @DisplayName("Falha Email Inválido")
        public void salvarAutor_emailFormatoInvalido_badRequest() throws Exception {
            final String nome = "Robert C. Martin (Uncle Bob)";
            final String email = "unclebobcleancoder.com";
            final String descricao = "Robert C. Martin (Uncle Bob) has been a programmer since 1970, is an international firm of highly experienced software developers and managers who specialize in helping companies get their projects done.";

            SalvarAutorRequest autorRequest = new SalvarAutorRequest(nome, email, descricao);
            executarMockMvcfail(autorRequest, "email", "O e-mail já cadastrado.");
        }

        @Test
        @DisplayName("Falha Email Duplicado")
        public void salvarAutor_emailDuplicado_badRequest() throws Exception {
            final String codigo = UUID.randomUUID().toString();
            final String nome = "Robert C. Martin (Uncle Bob)";
            final String email = "unclebob@cleancoder.com";
            final String descricao = "Robert C. Martin (Uncle Bob) has been a programmer since 1970, is an international firm of highly experienced software developers and managers who specialize in helping companies get their projects done.";

            when(autorRepository.findByEmail(anyString()))
                    .thenReturn(Optional.of(new Autor(codigo, nome, email, descricao, LocalDateTime.now())));

            SalvarAutorRequest autorRequest = new SalvarAutorRequest(nome, email, descricao);
            executarMockMvcfail(autorRequest, "email", "O e-mail já cadastrado.");
        }

        @Test
        @DisplayName("Falha descricao não preenchida")
        public void salvarAutor_descricaoNaoPreenchida_badRequest() throws Exception {
            final String nome = "Robert C. Martin (Uncle Bob)";
            final String email = "unclebob@cleancoder.com";

            when(autorRepository.findByEmail(anyString())).thenReturn(Optional.empty());

            SalvarAutorRequest autorRequest = new SalvarAutorRequest(nome, email, null);
            executarMockMvcfail(autorRequest, "descricao", "O campo 'descricao' deve ser preenchido e possuir o no máximo 400 caracteres.");
        }

        @Test
        @DisplayName("Falha descricao tamanho inválido")
        public void salvarAutor_tamanhoMaximoDescricao_badRequest() throws Exception {
            final String nome = "Robert C. Martin (Uncle Bob)";
            final String email = "unclebob@cleancoder.com";
            final String descricao = "Robert C. Martin (Uncle Bob) has been a programmer since 1970, is an international firm of highly experienced software developers and managers who specialize in helping companies get their projects done. " +
                    "Object Mentor offers process improvement consulting, object-oriented software design consulting, training, and skill development services to major corporations worldwide. " +
                    "A leader in the industry of software development, Mr. Martin served three years as the editor-in-chief of the C++ Report, and he served as the first chairman of the Agile Alliance.";

            SalvarAutorRequest autorRequest = new SalvarAutorRequest(nome, email, descricao);
            executarMockMvcfail(autorRequest, "descricao", "O campo 'descricao' deve ser preenchido e possuir o no máximo 400 caracteres.");
        }

        private final void executarMockMvcfail(SalvarAutorRequest autorRequest, String campo, String mensagem) throws Exception {
            mockMvc.perform(
                    post(URL_BASE)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(autorRequest)))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                    .andExpect(result -> assertTrue(result.getResolvedException().getMessage().startsWith("Validation failed for argument")))
                    .andExpect(jsonPath("$.statusCode", is(HttpStatus.BAD_REQUEST.value())))
                    .andExpect(jsonPath("$.message", equalTo("Method Argument Not Valid Exception")))
                    .andExpect(jsonPath("$.description", equalTo("Field Validation Error")))
                    .andExpect(jsonPath("$.timestamp", notNullValue()))
                    .andExpect(jsonPath("$.errors", notNullValue()))
                    .andExpect(jsonPath("$.errors", hasSize(1)))
                    .andExpect(jsonPath("$.errors[0].key", equalTo(campo)))
                    .andExpect(jsonPath("$.errors[0].value", equalTo(mensagem)));
        }
    }

}