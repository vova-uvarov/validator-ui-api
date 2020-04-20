package ru.openbbank.documentvalidator.repository.model.enums;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import ru.openbbank.documentvalidator.repository.model.RuleCheck;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Table
@Entity
public class FieldRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    SchemaType schemaType;
    Format format;

    Long documentTypeId;
    String fieldName;

    @Enumerated(EnumType.STRING)
    ValidationMode validationMode;

    @Enumerated(EnumType.STRING)
    RuleSideEnum ruleSide;
    String groupName;
    Boolean enabled;

    @Column(columnDefinition = "CLOB")
    String description;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "fieldRuleId", insertable = false, updatable = false)
    Set<RuleCheck> checks;


    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .build();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FieldRule)) {
            return false;
        }

        return new EqualsBuilder()
                .append(id, ((FieldRule) obj).getId())
                .build();
    }
}
