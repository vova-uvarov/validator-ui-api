package ru.openbank.validator.ui.web.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.openbank.validator.ui.model.FieldRuleFilter;
import ru.openbank.validator.ui.model.FieldRuleInfo;
import ru.openbank.validator.ui.service.FieldRuleService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ui/filedRule")
public class FieldRuleController {

    private final FieldRuleService service;

    @GetMapping("/search")
    Page<FieldRuleInfo> search(FieldRuleFilter filterDto,
                               @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return service.search(filterDto, pageable);
    }
}
