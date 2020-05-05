package ru.openbank.validator.ui.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.openbank.validator.ui.model.FieldRuleFilter;
import ru.openbank.validator.ui.model.FieldRuleInfo;
import ru.openbank.validator.ui.model.RuleCheckInfo;
import ru.openbbank.documentvalidator.repository.FieldRuleRepository;
import ru.openbbank.documentvalidator.repository.model.RuleCheck;
import ru.openbbank.documentvalidator.repository.model.enums.DocumentType;
import ru.openbbank.documentvalidator.repository.model.enums.FieldRule;
import ru.openbank.validator.ui.specification.FieldRuleSpecification;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FieldRuleService  implements IFieldRuleService{
    private final FieldRuleRepository repository;

    @Override
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
                        .sorted(Comparator.comparing(RuleCheckInfo::getId))
                        .collect(Collectors.toList())
                )
                .build());

    }

    @Override
    public Set<String> groupNames(List<DocumentType> documentTypes){
        if (CollectionUtils.isEmpty(documentTypes)){
            return repository.allGroupNames();
        }

        return documentTypes.stream()
                .map(repository::groupNamesByDocumentType)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> fieldNames(List<DocumentType> documentTypes){
        if (CollectionUtils.isEmpty(documentTypes)){
            return repository.allFields();
        }

        return documentTypes.stream()
                .map(repository::fieldsByDocumentType)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> ruleNames(List<DocumentType> documentTypes) {
        if (CollectionUtils.isEmpty(documentTypes)){
            return repository.allRuleNames();
        }

        return documentTypes.stream()
                .map(repository::ruleNamesByDocumentType)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
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
