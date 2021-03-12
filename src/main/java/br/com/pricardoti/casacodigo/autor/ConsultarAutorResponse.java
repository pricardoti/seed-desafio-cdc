package br.com.pricardoti.casacodigo.autor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;

public class ConsultarAutorResponse implements Serializable {

    private static final long serialVersionUID = 7630802429590156943L;

    private final String codigo;
    private final String nome;
    private final String email;
    private final String descricao;
    private final String criacao;

    public ConsultarAutorResponse(
            @NotNull final Autor autor
    ) {
        this.codigo = autor.getCodigo();
        this.nome = autor.getNome();
        this.email = autor.getEmail();
        this.descricao = autor.getDescricao();
        this.criacao = autor.getDataCriacao()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a"));
    }

    public String getCodigo() {
        return codigo;
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

    public String getCriacao() {
        return criacao;
    }
}
