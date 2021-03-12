package br.com.pricardoti.casacodigo.categoria;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 5, max = 50)
    @Column(unique = true, length = 50)
    private String nome;

    public Categoria(
            @NotBlank @Size(min = 3, max = 50) String nome
    ) {
        this.nome = nome;
    }

    public Categoria(Long id, @NotBlank @Size(min = 5, max = 50) String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
