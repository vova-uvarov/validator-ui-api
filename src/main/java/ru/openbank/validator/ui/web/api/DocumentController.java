package ru.openbank.validator.ui.web.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.openbank.validator.ui.model.DocumentInfo;
import ru.openbbank.documentvalidator.repository.model.DocumentRule;
import ru.openbbank.documentvalidator.repository.model.enums.FieldRule;
import ru.openbank.validator.ui.service.DocumentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ui/documents")
public class DocumentController {

    private final DocumentService documentRuleService;

    @GetMapping
    public List<DocumentInfo> documents() {
        List<DocumentInfo> result = new ArrayList<>();
        Iterable<DocumentRule> documentRules = documentRuleService.findAll();
        for (DocumentRule documentRule : documentRules) {
            result.add(toDocumentInfo(documentRule));
        }
        return result;
    }

    private DocumentInfo toDocumentInfo(DocumentRule documentRule) {
        Set<FieldRule> fieldRules = documentRule.getFieldRules();
        return DocumentInfo.builder()
                .documentRuleId(documentRule.getId())
                .documentType(documentRule.getDocumentType())
                .fieldRuleCounts(fieldRules.stream()
                        .collect(Collectors.groupingBy(FieldRule::getRuleSide, Collectors.counting())))
                .count(fieldRules.size())
                .countDisabled((int) fieldRules.stream().filter(r -> !r.getEnabled()).count())
                .build();
    }
}
