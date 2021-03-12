package br.com.pricardoti.casacodigo.livro;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class FormatoAttributeConverter implements AttributeConverter<FormatoEnum, Character> {

    @Override
    public Character convertToDatabaseColumn(FormatoEnum formatoEnum) {
        return formatoEnum.getCodigo();
    }

    @Override
    public FormatoEnum convertToEntityAttribute(Character codigo) {
        return FormatoEnum.findByCodigo(codigo);
    }
}
