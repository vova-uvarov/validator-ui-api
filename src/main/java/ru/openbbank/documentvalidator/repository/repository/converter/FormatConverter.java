package ru.openbbank.documentvalidator.repository.repository.converter;


import ru.openbbank.documentvalidator.repository.model.enums.Format;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class FormatConverter implements AttributeConverter<Format, String> {
    @Override
    public String convertToDatabaseColumn(Format source) {
        return source != null ? source.getValue() : null;
    }

    @Override
    public Format convertToEntityAttribute(String source) {
        return source != null ? Format.of(source) : null;
    }
}
