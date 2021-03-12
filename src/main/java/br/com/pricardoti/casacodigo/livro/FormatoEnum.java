package br.com.pricardoti.casacodigo.livro;

import java.util.Optional;
import java.util.stream.Stream;

public enum FormatoEnum {

    DIGITAL('D', "Digital"),
    CAPA_COMUM('C', "Capa Comum");

    private Character codigo;
    private String descricao;

    FormatoEnum(Character codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Character getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static FormatoEnum findByCodigo(Character codigo) {
        Optional.ofNullable(codigo).orElseThrow(IllegalArgumentException::new);
        return Stream.of(values()).parallel()
                .filter(item -> item.codigo.equals(codigo))
                .findFirst().get();
    }

}
