package br.com.pricardoti.casacodigo.autor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class SalvarAutorResponse implements Serializable {

    private static final long serialVersionUID = 4160443464120156548L;
    private String codigo;

    public SalvarAutorResponse(@NotNull final Autor autor) {
        this.codigo = autor.getCodigo();
    }

    public String getCodigo() {
        return codigo;
    }
}
