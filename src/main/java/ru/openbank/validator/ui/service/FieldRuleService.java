package ru.openbank.validator.ui.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.openbank.validator.ui.model.FieldRuleFilter;
import ru.openbank.validator.ui.model.FieldRuleInfo;
import ru.openbank.validator.ui.model.RuleCheckInfo;
import ru.openbbank.documentvalidator.repository.repository.FieldRuleRepository;
import ru.openbbank.documentvalidator.repository.model.RuleCheck;
import ru.openbbank.documentvalidator.repository.model.enums.FieldRule;
import ru.openbank.validator.ui.specification.FieldRuleSpecification;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FieldRuleService {
    private final FieldRuleRepository repository;

    public Page<FieldRuleInfo> search(FieldRuleFilter filter, Pageable pageable) {
        Page<FieldRule> fieldRules = repository.findAll(new FieldRuleSpecification(filter), pageable);
        return fieldRules.map(f -> FieldRuleInfo.builder()
                .id(f.getId())
                .name(f.getName())
                .schemaType(f.getSchemaType())
                .documentTypeId(f.getDocumentTypeId())
                .format(f.getFormat())
                .fieldName(f.getFieldName())
                .validationMode(f.getValidationMode())
                .ruleSide(f.getRuleSide())
                .groupName(f.getGroupName())
                .enabled(f.getEnabled())
                .description(f.getDescription())
                .ruleChecks(f.getChecks().stream()
                        .map(this::map)
                        .collect(Collectors.toList())
                )
                .build());

    }

    private RuleCheckInfo map(RuleCheck check) {
        if (check == null) {
            return null;
        }
        return RuleCheckInfo.builder()
                .id(check.getId())
                .keyword(check.getValue())
                .parameters(check.getParameters())
                .message(check.getMessage())
                .conditionalRuleCheck(map(check.getConditionalRuleCheck()))
                .build();
    }
}
