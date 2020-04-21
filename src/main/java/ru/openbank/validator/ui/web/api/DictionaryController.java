package ru.openbank.validator.ui.web.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.openbank.validator.ui.config.DocumentTypesProperties;
import ru.openbank.validator.ui.model.DictionaryRecord;
import ru.openbbank.documentvalidator.repository.model.enums.DocumentType;
import ru.openbbank.documentvalidator.repository.model.enums.Format;
import ru.openbbank.documentvalidator.repository.model.enums.KeywordEnum;
import ru.openbbank.documentvalidator.repository.model.enums.RuleSideEnum;
import ru.openbbank.documentvalidator.repository.model.enums.SchemaType;
import ru.openbbank.documentvalidator.repository.model.enums.ValidationMode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ui/dictionary")
public class DictionaryController {

    private final DocumentTypesProperties documentTypesProperties;

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
