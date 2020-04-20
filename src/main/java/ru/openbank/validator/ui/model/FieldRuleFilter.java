package ru.openbank.validator.ui.model;

import lombok.Data;
import ru.openbbank.documentvalidator.repository.model.enums.DocumentType;
import ru.openbbank.documentvalidator.repository.model.enums.Format;
import ru.openbbank.documentvalidator.repository.model.enums.KeywordEnum;
import ru.openbbank.documentvalidator.repository.model.enums.RuleSideEnum;
import ru.openbbank.documentvalidator.repository.model.enums.SchemaType;
import ru.openbbank.documentvalidator.repository.model.enums.ValidationMode;

import java.util.Set;

@Data
public class FieldRuleFilter {
    private Set<DocumentType> documentTypes;
    private Set<SchemaType> schemaTypes;
    private String name;
    private Set<Format> formats;
    private String fieldName;
    private SearchMode filedRuleSearchMode;
    private ValidationMode validationMode;
    private Set<RuleSideEnum> ruleSides;
    private String groupName;
    private Boolean enabled;
    private String description;

    private String ruleCheckMessage;
    private Set<KeywordEnum> keywords;
}
