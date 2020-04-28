package ru.openbank.validator.ui.web.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.openbank.validator.ui.config.DocumentTypesProperties;
import ru.openbank.validator.ui.model.DictionaryRecord;
import ru.openbank.validator.ui.service.IFieldRuleService;
import ru.openbbank.documentvalidator.repository.model.enums.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ui/dictionary")
public class DictionaryController {

    private final DocumentTypesProperties documentTypesProperties;
    private final IFieldRuleService fieldRuleService;

    @GetMapping("/fieldNames")
    List<DictionaryRecord> fieldNames(@RequestParam(required = false) List<DocumentType> documentTypes) {
        Set<String> fieldNames = fieldRuleService.fieldNames(documentTypes);
        return fieldNames.stream()
                .sorted()
                .map(k -> new DictionaryRecord(k, k))
                .collect(Collectors.toList());
    }

    @GetMapping("/groupNames")
    List<DictionaryRecord> groupNames(@RequestParam(required = false) List<DocumentType> documentTypes) {
        Set<String> names = fieldRuleService.groupNames(documentTypes);
        return names.stream()
                .sorted()
                .map(k -> new DictionaryRecord(k, k))
                .collect(Collectors.toList());
    }

    @GetMapping("/ruleNames")
    List<DictionaryRecord> ruleNames(@RequestParam(required = false) List<DocumentType> documentTypes) {
        Set<String> names = fieldRuleService.ruleNames(documentTypes);
        return names.stream()
                .sorted()
                .map(k -> new DictionaryRecord(k, k))
                .collect(Collectors.toList());
    }

    @GetMapping("/ruleSides")
    List<DictionaryRecord> ruleSides() {
        return Arrays.stream(RuleSideEnum.values())
                .map(k -> new DictionaryRecord(k.name(), k.name()))
                .collect(Collectors.toList());
    }

    @GetMapping("/keywords")
    List<DictionaryRecord> keywords() {
        return Arrays.stream(KeywordEnum.values())
                .map(k -> new DictionaryRecord(k.name(), k.getValue()))
                .collect(Collectors.toList());
    }

    @GetMapping("/formats")
    List<DictionaryRecord> formats() {
        return Arrays.stream(Format.values())
                .map(k -> new DictionaryRecord(k.name(), k.getValue()))
                .collect(Collectors.toList());
    }

    @GetMapping("/validationModes")
    List<DictionaryRecord> validationModes() {
        return Arrays.stream(ValidationMode.values())
                .map(k -> new DictionaryRecord(k.name(), k.name()))
                .collect(Collectors.toList());
    }

    @GetMapping("/schemaTypes")
    List<DictionaryRecord> schemaTypes() {
        return Arrays.stream(SchemaType.values())
                .map(k -> new DictionaryRecord(k.name(), k.name()))
                .collect(Collectors.toList());
    }

    @GetMapping("/documentTypes")
    List<DictionaryRecord> documentTypes() {
        return Arrays.stream(DocumentType.values())
                .map(k -> new DictionaryRecord(k.name(), documentTypesProperties.getTypes().getOrDefault(k, k.name())))
                .collect(Collectors.toList());
    }
}
