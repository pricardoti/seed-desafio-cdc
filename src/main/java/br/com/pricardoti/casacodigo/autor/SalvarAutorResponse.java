package br.com.pricardoti.casacodigo.autor;

import javax.validation.constraints.NotNull;

public class SalvarAutorResponse {

    private String codigo;

    public SalvarAutorResponse(
            @NotNull final Autor autor
    ) {
        this.codigo = autor.getCodigo();
    }

    public String getCodigo() {
        return codigo;
    }
}
