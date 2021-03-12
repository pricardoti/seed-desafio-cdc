package br.com.pricardoti.casacodigo.autor;

import br.com.pricardoti.casacodigo.commons.validation.email.EmailUnico;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Autor implements Serializable {

    private static final long serialVersionUID = 4476175454986289050L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 36, unique = true, nullable = false)
    private String codigo = UUID.randomUUID().toString();

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @EmailUnico
    @Column(nullable = false)
    private String email;

    @Size(max = 400)
    @NotBlank
    @Column(length = 400, nullable = false)
    private String descricao;

    @PastOrPresent
    @Column(nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    public Autor() {
    }

    public Autor(
            String codigo,
            @NotBlank String nome,
            @NotBlank @EmailUnico String email,
            @Size(max = 400) @NotBlank String descricao,
            @PastOrPresent LocalDateTime dataCriacao
    ) {
        this(nome, email, descricao);
        this.codigo = codigo;
        this.dataCriacao = dataCriacao;
    }

    public Autor(
            @NotBlank String nome,
            @Email @NotBlank String email,
            @Size(max = 400) @NotBlank String descricao
    ) {
        this.email = email;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
