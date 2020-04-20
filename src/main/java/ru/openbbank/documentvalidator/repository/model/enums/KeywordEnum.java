package ru.openbbank.documentvalidator.repository.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum KeywordEnum {
    MINIMUM("minimum"),
    MAXIMUM("maximum"),
    MULTIPLE_OF("multipleOf"),
    EXCLUSIVE_MAXIMUM("exclusiveMaximum"),
    EXCLUSIVE_MINIMUM("exclusiveMinimum"),

    PATTERN("pattern"),
    MIN_LENGTH("minLength"),
    MIN_LENGTH_CONDITIONAL("minLengthConditional"),
    MAX_LENGTH("maxLength"),

    TYPE("type"),
    ITEMS("items"),
    MAX_ITEMS("maxItems"),
    MIN_ITEMS("minItems"),
    UNIQUE_ITEMS("uniqueItems"),
    ADDITIONAL_ITEMS("additionalItems"),

    PROPERTIES("properties"),
    DEPENDENCIES("dependencies"),
    PATTERN_PROPERTIES("patternProperties"),
    ADDITIONAL_PROPERTIES("additionalProperties"),

    ENUM("enum"),
    FORMAT("format"),
    REQUIRED("required"),

    REQUIRED_IF("requiredIf"),
    REQUIRED_IF_2("requiredIf2"),
    REQUIRED_IF_3("requiredIf3"),
    REQUIRED_IF_4("requiredIf4"),
    DATE_INTERVAL("dateInterval"),
    DATE_MINIMUM("dateMinimum"),
    DATE_MAXIMUM("dateMaximum"),
    REFERENCE_EQUAL("referenceEqual"),
    REFERENCE_EQUAL_2("referenceEqual2"),
    REFERENCE_EQUAL_3("referenceEqual3"),
    JOIN_REFERENCE_EXISTS("joinReferenceExists"),
    JOIN_REFERENCE_EXISTS_2("joinReferenceExists2"),
    REFERENCE_NOT_EQUAL("referenceNotEqual"),
    REFERENCE_EQUAL_OR_DEFAULT("referenceEqualOrDefault"),
    REFERENCE_COMPARE("referenceCompare"),
    REFERENCE_COMPARE_2("referenceCompare2"),
    REFERENCE_DUPLICATE("referenceDuplicate"),
    SIZE("size"),
    EQUAL("equal"),
    NOT_EQUAL("notEqual"),
    CHECK_ACCOUNT("checkAccount"),
    CHECK_ACCOUNT_KEY("checkAccountKey"),
    CHECK_CURRENCY_AMOUNT("checkCurrencyAmount"),
    CHECK_CURRENCY_AMOUNT2("checkCurrencyAmount2"),
    ARRAY_REQUIRED_IF("arrayRequiredIf"),
    PRECISION("precision"),
    UNIQUE_DOCUMENT("uniqueDocument"),
    EXPRESSION("expression"),
    EXPRESSION_2("expression2"),
    EXPRESSION_3("expression3"),
    FILE_EXTENSION("fileExtension"),

    MODE("mode"),
    ERROR_MESSAGE("errorMessage"),
    GROUP("group"),

    CONDITION("condition"),
    CONDITION_2("condition2"),
    CONDITION_3("condition3"),
    CONDITION_4("condition4"),
    ALWAYS_FAIL("alwaysFail"),
    SUBSTRING("substring"),
    SUBSTRING_2("substring2"),
    SUBSTRING_3("substring3");


    @JsonValue
    private String value;

    public static KeywordEnum of(String source) {
        return Arrays.stream(values())
                .filter(v -> Objects.equals(v.value, source.replaceAll("\\[\\d+]","")))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("unknown value " + source));
    }
}
