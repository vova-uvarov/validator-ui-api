package ru.openbank.validator.ui.model;

import lombok.Builder;
import lombok.Data;
import ru.openbbank.documentvalidator.repository.model.enums.DocumentType;
import ru.openbbank.documentvalidator.repository.model.enums.RuleSideEnum;

import java.util.Map;

@Data
@Builder
public class DocumentInfo {
    private Long documentRuleId;
    private DocumentType documentType;
    private Map<RuleSideEnum, Long> fieldRuleCounts;
    private int count;
    private int countDisabled;
}
