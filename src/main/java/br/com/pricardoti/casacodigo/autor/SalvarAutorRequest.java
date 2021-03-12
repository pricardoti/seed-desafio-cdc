package br.com.pricardoti.casacodigo.autor;

import br.com.pricardoti.casacodigo.commons.validation.email.EmailUnico;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SalvarAutorRequest {

    @NotBlank(message = "{notblank.autor.nome}")
    private String nome;

    @EmailUnico
    @NotBlank(message = "{notblank.autor.email}")
    private String email;

    @Size(max = 400, message = "{size.autor.descricao}")
    @NotBlank(message = "{size.autor.descricao}")
    private String descricao;

    public SalvarAutorRequest() {
    }

    public SalvarAutorRequest(
            @NotBlank String nome,
            @Email @NotBlank String email,
            @Size(max = 400) @NotBlank String descricao
    ) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "SalvarAutorRequest{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    protected Autor getAutor() {
        return new Autor(this.nome, this.email, this.descricao);
    }
}
