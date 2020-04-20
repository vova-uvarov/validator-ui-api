package ru.openbank.validator.ui.model;

import lombok.Builder;
import lombok.Data;
import ru.openbbank.documentvalidator.repository.model.enums.Format;
import ru.openbbank.documentvalidator.repository.model.enums.RuleSideEnum;
import ru.openbbank.documentvalidator.repository.model.enums.SchemaType;
import ru.openbbank.documentvalidator.repository.model.enums.ValidationMode;

import java.util.List;

@Data
@Builder
public class FieldRuleInfo {
    Long id;
    String name;
    SchemaType schemaType;
    Format format;
    Long documentTypeId;
    String fieldName;
    ValidationMode validationMode;
    RuleSideEnum ruleSide;
    String groupName;
    Boolean enabled;
    String description;
    List<RuleCheckInfo> ruleChecks;
}
