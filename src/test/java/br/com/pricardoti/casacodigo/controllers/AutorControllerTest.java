package br.com.pricardoti.casacodigo.controllers;

import br.com.pricardoti.casacodigo.autor.Autor;
import br.com.pricardoti.casacodigo.autor.AutorController;
import br.com.pricardoti.casacodigo.autor.AutorRepository;
import br.com.pricardoti.casacodigo.autor.AutorRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Autor Controller Test")
@ExtendWith(SpringExtension.class)
@WebMvcTest(AutorController.class)
public class AutorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AutorRepository autorRepository;

    private final String URL_BASE = "/autores";

    @Test
    @DisplayName("Salvar Autor - Sucesso")
    public void deveSalvarAutor_sucesso() throws Exception {
        final String nome = "Robert C. Martin (Uncle Bob)";
        final String email = "unclebob@cleancoder.com";
        final String descricao = "Robert C. Martin (Uncle Bob) has been a programmer since 1970, is an international firm of highly experienced software developers and managers who specialize in helping companies get their projects done.";
        final String codigo = UUID.randomUUID().toString();

        when(autorRepository.save(any(Autor.class))).thenReturn(new Autor(codigo, nome, email, descricao, LocalDateTime.now()));

        AutorRequest autorRequest = new AutorRequest(nome, email, descricao);
        mockMvc.perform(
                post(URL_BASE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(autorRequest.toString()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.codigo", is(codigo)));
    }

    @Test
    @DisplayName("Salvar Autor - Falha Nome")
    public void deveSalvarAutor_nomeNaoPreenchido_badRequest() throws Exception {
        final String codigo = UUID.randomUUID().toString();
        final String nome = "Robert C. Martin (Uncle Bob)";
        final String email = "unclebobcleancoder.com";
        final String descricao = "Robert C. Martin (Uncle Bob) has been a programmer since 1970, is an international firm of highly experienced software developers and managers who specialize in helping companies get their projects done.";

        when(autorRepository.save(any(Autor.class))).thenReturn(new Autor(codigo, nome, email, descricao, LocalDateTime.now()));

        AutorRequest autorRequest = new AutorRequest(null, email, descricao);
        mockMvc.perform(
                post(URL_BASE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(autorRequest.toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Salvar Autor - Falha Email")
    public void deveSalvarAutor_emailFormatoInvalido_badRequest() throws Exception {
        final String codigo = UUID.randomUUID().toString();
        final String nome = "Robert C. Martin (Uncle Bob)";
        final String email = "unclebobcleancoder.com";
        final String descricao = "Robert C. Martin (Uncle Bob) has been a programmer since 1970, is an international firm of highly experienced software developers and managers who specialize in helping companies get their projects done.";

        when(autorRepository.save(any(Autor.class))).thenReturn(new Autor(codigo, nome, email, descricao, LocalDateTime.now()));

        AutorRequest autorRequest = new AutorRequest(nome, email, descricao);
        mockMvc.perform(
                post(URL_BASE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(autorRequest.toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Salvar Autor - Falha Descricao")
    public void deveSalvarAutor_tamanhoMaximoDescricao_badRequest() throws Exception {
        final String codigo = UUID.randomUUID().toString();
        final String nome = "Robert C. Martin (Uncle Bob)";
        final String email = "unclebobcleancoder.com";
        final String descricao = "Robert C. Martin (Uncle Bob) has been a programmer since 1970, is an international firm of highly experienced software developers and managers who specialize in helping companies get their projects done. " +
                "Object Mentor offers process improvement consulting, object-oriented software design consulting, training, and skill development services to major corporations worldwide. " +
                "A leader in the industry of software development, Mr. Martin served three years as the editor-in-chief of the C++ Report, and he served as the first chairman of the Agile Alliance.";

        when(autorRepository.save(any(Autor.class))).thenReturn(new Autor(codigo, nome, email, descricao, LocalDateTime.now()));

        AutorRequest autorRequest = new AutorRequest(nome, email, descricao);
        mockMvc.perform(
                post(URL_BASE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(autorRequest.toString()))
                .andExpect(status().isBadRequest());
    }
}