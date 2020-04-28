package ru.openbbank.documentvalidator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.openbbank.documentvalidator.repository.model.enums.DocumentType;
import ru.openbbank.documentvalidator.repository.model.enums.FieldRule;

import java.util.List;
import java.util.Set;

public interface FieldRuleRepository extends JpaRepository<FieldRule, Long>, JpaSpecificationExecutor<FieldRule> {

    FieldRule getByDocumentTypeIdAndName(Long documentTypeId, String name);

    @Query("select distinct fr.fieldName from FieldRule fr where fr.documentTypeId=(select id from DocumentRule  where documentType=:documentType)")
    Set<String> fieldsByDocumentType(@Param("documentType") DocumentType documentType);

    @Query("select distinct fr.fieldName from FieldRule fr")
    Set<String> allFields();

    @Query("select distinct fr.groupName from FieldRule fr where fr.documentTypeId=(select id from DocumentRule  where documentType=:documentType)")
    Set<String> groupNamesByDocumentType(@Param("documentType") DocumentType documentType);

    @Query("select distinct fr.groupName from FieldRule fr")
    Set<String> allGroupNames();

    @Query("select distinct fr.name from FieldRule fr where fr.documentTypeId=(select id from DocumentRule  where documentType=:documentType)")
    Set<String> ruleNamesByDocumentType(@Param("documentType") DocumentType documentType);

    @Query("select distinct fr.name from FieldRule fr")
    Set<String> allRuleNames();

}
