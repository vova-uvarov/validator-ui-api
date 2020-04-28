package ru.openbank.validator.ui.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.openbank.validator.ui.model.FieldRuleFilter;
import ru.openbank.validator.ui.model.FieldRuleInfo;
import ru.openbbank.documentvalidator.repository.model.enums.DocumentType;

import java.util.List;
import java.util.Set;

public interface IFieldRuleService {
    Page<FieldRuleInfo> search(FieldRuleFilter filter, Pageable pageable);

    Set<String> groupNames(List<DocumentType> documentTypes);

    Set<String> fieldNames(List<DocumentType> documentTypes);

    Set<String> ruleNames(List<DocumentType> documentTypes);
}
