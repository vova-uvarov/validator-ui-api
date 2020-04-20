package ru.openbbank.documentvalidator.repository.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum Format {
    DATE("date");

    private String value;

    public static Format of(String source) {
        return Arrays.stream(values())
                .filter(v -> Objects.equals(v.value, source))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("unknown value " + source));
    }
}
