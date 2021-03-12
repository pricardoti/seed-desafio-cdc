package br.com.pricardoti.casacodigo.categoria;

import java.io.Serializable;

public class SalvarCategoriaResponse implements Serializable {

    private static final long serialVersionUID = -7173865822153661304L;

    private Long id;
    private String nome;

    public SalvarCategoriaResponse(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
