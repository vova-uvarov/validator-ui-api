package ru.openbank.validator.ui.model;

import lombok.Builder;
import lombok.Data;
import ru.openbbank.documentvalidator.repository.model.enums.KeywordEnum;

@Data
@Builder
public class RuleCheckInfo {

    Long id;
    KeywordEnum keyword;
    String parameters;
    String message;
    RuleCheckInfo conditionalRuleCheck;
}
