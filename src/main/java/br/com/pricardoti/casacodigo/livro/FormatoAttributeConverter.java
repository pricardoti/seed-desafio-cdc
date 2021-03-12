package br.com.pricardoti.casacodigo.livro;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import javax.persistence.Converter;

@Converter(autoApply = false)
public class FormatoAttributeConverter implements AttributeConverter<FormatoEnum, Character> {

    @Override
    public Character convertToDatabaseColumn(FormatoEnum formatoEnum) {
        return null;
    }

    @Override
    public FormatoEnum convertToEntityAttribute(Character character) {
        return null;
    }
}
