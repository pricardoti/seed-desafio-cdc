package br.com.pricardoti.casacodigo.autor;

import br.com.pricardoti.casacodigo.commons.validation.BasicField;
import br.com.pricardoti.casacodigo.commons.validation.email.EmailUnico;

import javax.validation.GroupSequence;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

@GroupSequence({BasicField.class, SalvarAutorRequest.class})
public class SalvarAutorRequest {

    @NotBlank(groups = BasicField.class, message = "{notblank.autor.nome}")
    private String nome;

    @NotEmpty(groups = BasicField.class, message = "{notblank.autor.email}")
    @EmailUnico(groups = Default.class)
    private String email;

    @Size(max = 400, message = "{size.autor.descricao}")
    @NotBlank(groups = BasicField.class, message = "{size.autor.descricao}")
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

    protected Autor getAutor() {
        return new Autor(this.nome, this.email, this.descricao);
    }
}
