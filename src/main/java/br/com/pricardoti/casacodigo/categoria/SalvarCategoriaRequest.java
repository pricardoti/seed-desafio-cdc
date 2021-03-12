package br.com.pricardoti.casacodigo.categoria;

import br.com.pricardoti.casacodigo.commons.validation.unique.UniqueValue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class SalvarCategoriaRequest implements Serializable {

    private static final long serialVersionUID = 6576545701924021549L;

    @NotBlank
    @Size(min = 5, max = 50)
    @UniqueValue(fieldName = "nome", domainClass = Categoria.class)
    private String nome;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Categoria toCategoria() {
        return new Categoria(this.nome);
    }
}
