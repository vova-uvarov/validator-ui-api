package ru.openbank.validator.ui.web.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.openbank.validator.ui.model.DictionaryRecord;
import ru.openbbank.documentvalidator.repository.model.enums.KeywordEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ui/dictionary")
public class DictionaryController {
    @GetMapping("/keywords")
    List<DictionaryRecord> keywords() {
        return Arrays.stream(KeywordEnum.values())
                .map(k -> new DictionaryRecord(k.name(), k.getValue()))
                .collect(Collectors.toList());
    }
}
