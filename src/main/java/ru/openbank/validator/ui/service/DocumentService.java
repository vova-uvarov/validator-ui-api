package ru.openbank.validator.ui.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.openbbank.documentvalidator.repository.repository.DocumentRuleRepository;
import ru.openbbank.documentvalidator.repository.model.DocumentRule;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRuleRepository repository;

    public Iterable<DocumentRule> findAll(){
        return repository.findAll();
    }
}
