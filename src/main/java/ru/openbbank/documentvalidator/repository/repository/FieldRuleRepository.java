package ru.openbbank.documentvalidator.repository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.openbbank.documentvalidator.repository.model.enums.FieldRule;

public interface FieldRuleRepository extends JpaRepository<FieldRule, Long>, JpaSpecificationExecutor<FieldRule> {

    FieldRule getByDocumentTypeIdAndName(Long documentTypeId, String name);

}
