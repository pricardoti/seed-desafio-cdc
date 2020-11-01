package br.com.pricardoti.casacodigo.autor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AutorRequest {

    @NotBlank
    private String nome;

    @Email
    @NotBlank
    private String email;

    @Size(max = 400)
    @NotBlank
    private String descricao;

    public AutorRequest(
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
        return "AutorRequest{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    protected Autor toAutor() {
        return new Autor(this.nome, this.email, this.descricao);
    }
}
