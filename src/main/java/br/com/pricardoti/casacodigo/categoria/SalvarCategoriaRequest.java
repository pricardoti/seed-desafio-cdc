package br.com.pricardoti.casacodigo.categoria;

import br.com.pricardoti.casacodigo.commons.validation.unique.UniqueValue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SalvarCategoriaRequest {

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

    public Categoria convertToCategoria() {
        return new Categoria(this.nome);
    }
}
