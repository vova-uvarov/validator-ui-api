package ru.openbbank.documentvalidator.repository.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import ru.openbbank.documentvalidator.repository.model.DocumentRule;
import ru.openbbank.documentvalidator.repository.model.enums.DocumentType;

public interface DocumentRuleRepository extends CrudRepository<DocumentRule, Long>, JpaSpecificationExecutor<DocumentRule> {

    DocumentRule getByDocumentType(DocumentType documentType);

}
