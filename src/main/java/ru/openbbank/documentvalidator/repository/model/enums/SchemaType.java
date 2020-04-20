package ru.openbbank.documentvalidator.repository.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum SchemaType {
    ANY("any", new Object()),
    OBJECT("object", new Object()),
    ARRAY("array", Collections.emptyList()),
    STRING("string", ""),
    NUMBER("number", 0.0),
    INTEGER("integer", 0),
    BOOLEAN("boolean", false);

    private String value;
    private Object defaultValue;

    public static SchemaType of(String source) {
        return Arrays.stream(values())
                .filter(v -> Objects.equals(v.value, source))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("unknown value " + source));
    }
}
