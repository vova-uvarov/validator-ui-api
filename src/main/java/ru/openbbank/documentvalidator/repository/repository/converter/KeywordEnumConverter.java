package ru.openbbank.documentvalidator.repository.repository.converter;

import ru.openbbank.documentvalidator.repository.model.enums.KeywordEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class KeywordEnumConverter implements AttributeConverter<KeywordEnum, String> {
    @Override
    public String convertToDatabaseColumn(KeywordEnum source) {
        return source != null ? source.getValue() : null;
    }

    @Override
    public KeywordEnum convertToEntityAttribute(String source) {
        return source != null ? KeywordEnum.of(source) : null;
    }
}
