package ru.openbank.validator.ui.specification;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import ru.openbank.validator.ui.model.FieldRuleFilter;
import ru.openbank.validator.ui.model.SearchMode;
import ru.openbbank.documentvalidator.repository.model.DocumentRule;
import ru.openbbank.documentvalidator.repository.model.RuleCheck;
import ru.openbbank.documentvalidator.repository.model.enums.FieldRule;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Data
@AllArgsConstructor
public class FieldRuleSpecification implements Specification<FieldRule> {
    private FieldRuleFilter filter;

    @Override
    public Predicate toPredicate(Root<FieldRule> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        PredicateBuilderHelper helper = new PredicateBuilderHelper(root, builder);
        List<Predicate> predicates = new ArrayList<>();
        Consumer<Predicate> add = predicates::add;
        helper.like("description", filter.getDescription()).ifPresent(add);
        helper.like("groupName", filter.getGroupName()).ifPresent(add);
        helper.like("name", filter.getName()).ifPresent(add);

        String fieldName = filter.getFieldName();
        if (filter.getFiledRuleSearchMode() == SearchMode.EQUALS) {
            helper.eq("fieldName", fieldName);
        } else {
            helper.like("fieldName", fieldName).ifPresent(add);
        }

        if (!CollectionUtils.isEmpty(filter.getDocumentTypes())) {
            Subquery<DocumentRule> documentRuleSubquery = query.subquery(DocumentRule.class);
            Root<DocumentRule> documentRuleRoot = documentRuleSubquery.from(DocumentRule.class);
            documentRuleSubquery.select(documentRuleRoot.get("id"));
            documentRuleSubquery.where(documentRuleRoot.get("documentType").in(filter.getDocumentTypes()));
            predicates.add(root.get("documentTypeId").in(documentRuleSubquery));
        }

        helper.eq("validationMode", filter.getValidationMode()).ifPresent(add);
        helper.eq("enabled", filter.getEnabled()).ifPresent(add);
        helper.in("format", filter.getFormats()).ifPresent(add);
        helper.in("schemaType", filter.getSchemaTypes()).ifPresent(add);
        helper.in("ruleSide", filter.getRuleSides()).ifPresent(add);

        Join<Object, RuleCheck> checksJoin = root.join("checks");
        if (StringUtils.isNotBlank(filter.getRuleCheckMessage())){
            predicates.add(builder.like(builder.lower(checksJoin.get("message")), "%" + filter.getRuleCheckMessage().toLowerCase() + "%"));
        }

        if (!CollectionUtils.isEmpty(filter.getKeywords())){
            predicates.add(checksJoin.get("value").in(filter.getKeywords()));
        }
        query.distinct(true);

        if (predicates.size() == 0) {
            return null;
        }
        return predicates.size() > 1
                ? builder.and(predicates.toArray(new Predicate[0]))
                : predicates.get(0);
    }

    @AllArgsConstructor
    private static class PredicateBuilderHelper {
        Root<FieldRule> root;
        CriteriaBuilder builder;

        Optional<Predicate> like(String fieldName, String value) {
            if (StringUtils.isNotBlank(value)) {
                return Optional.ofNullable(builder.like(builder.lower(root.get(fieldName)), "%" + value.toLowerCase() + "%"));
            }
            return Optional.empty();
        }

        Optional<Predicate> eq(String fieldName, Object value) {
            if (value != null) {
                return Optional.ofNullable(builder.equal(root.get(fieldName), value));
            }
            return Optional.empty();
        }

        Optional<Predicate> in(String fieldName, Collection<?> values) {
            if (!CollectionUtils.isEmpty(values)) {
                return Optional.ofNullable(root.get(fieldName).in(values));
            }
            return Optional.empty();
        }
    }

}
