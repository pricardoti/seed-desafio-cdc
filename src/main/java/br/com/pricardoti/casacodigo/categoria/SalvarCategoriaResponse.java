package br.com.pricardoti.casacodigo.categoria;

public class SalvarCategoriaResponse {

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
