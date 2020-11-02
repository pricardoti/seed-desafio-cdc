package br.com.pricardoti.casacodigo.core.error;

public class ItemErrorDetail {
    private final String campo;
    private final String descricao;

    public ItemErrorDetail(String nome, String descricao) {
        this.campo = nome;
        this.descricao = descricao;
    }

    public String getCampo() {
        return campo;
    }

    public String getDescricao() {
        return descricao;
    }
}
