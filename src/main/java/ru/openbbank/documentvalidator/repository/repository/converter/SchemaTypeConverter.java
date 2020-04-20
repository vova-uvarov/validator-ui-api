package ru.openbbank.documentvalidator.repository.repository.converter;

import ru.openbbank.documentvalidator.repository.model.enums.SchemaType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class SchemaTypeConverter implements AttributeConverter<SchemaType, String> {
    @Override
    public String convertToDatabaseColumn(SchemaType source) {
        return source != null ? source.getValue() : null;
    }

    @Override
    public SchemaType convertToEntityAttribute(String source) {
        return source != null ? SchemaType.of(source) : null;
    }
}
