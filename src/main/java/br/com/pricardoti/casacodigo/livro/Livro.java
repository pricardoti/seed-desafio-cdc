package br.com.pricardoti.casacodigo.livro;

import br.com.pricardoti.casacodigo.autor.Autor;
import br.com.pricardoti.casacodigo.categoria.Categoria;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Livro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nome;
    private String resumo;
    private String sumario;
    private String isn;

    private Double preco;
    private Integer numeroPaginas;

    private LocalDate dataPublicacao;
    private Categoria categoria;
    private Autor autor;

    @Convert(converter = FormatoAttributeConverter.class)
    private FormatoEnum formato;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public void setSumario(String sumario) {
        this.sumario = sumario;
    }

    public String getIsn() {
        return isn;
    }

    public void setIsn(String isn) {
        this.isn = isn;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(Integer numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public FormatoEnum getFormato() {
        return formato;
    }

    public void setFormato(FormatoEnum formato) {
        this.formato = formato;
    }
}
