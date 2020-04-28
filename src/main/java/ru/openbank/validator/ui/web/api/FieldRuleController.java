package ru.openbank.validator.ui.web.api;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.openbank.validator.ui.model.FieldRuleFilter;
import ru.openbank.validator.ui.model.FieldRuleInfo;
import ru.openbank.validator.ui.service.IFieldRuleService;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ui/filedRule")
public class FieldRuleController {

    private final IFieldRuleService service;

    @GetMapping("/search")
    Page<FieldRuleInfo> search(FieldRuleFilter filterDto,
                               @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        filterDto.setFieldNames(
                filterDto.getFieldNames().stream()
                        .map(this::decode)
                        .collect(Collectors.toSet())
        );
        return service.search(filterDto, pageable);
    }

    @SneakyThrows
    private String decode(String f) {
        return URLDecoder.decode(f, StandardCharsets.UTF_8);
    }
}
