package ru.openbbank.documentvalidator.repository.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import ru.openbbank.documentvalidator.repository.model.enums.FieldRule;
import ru.openbbank.documentvalidator.repository.model.enums.KeywordEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@ToString(exclude = {"conditionalRuleCheck", "fieldRule"})
@Table
@Entity
public class RuleCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long fieldRuleId;

    @OneToOne
    @JoinColumn(name = "fieldRuleId", insertable = false, updatable = false)
    private FieldRule fieldRule;

    private KeywordEnum value;

    @Column(columnDefinition = "CLOB")
    private String parameters;

    @Column(columnDefinition = "CLOB")
    private String message;

    @OneToOne
    @JoinColumn(name = "conditionalRuleCheckId")
    private RuleCheck conditionalRuleCheck;


    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .build();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RuleCheck)) {
            return false;
        }

        return new EqualsBuilder()
                .append(id, ((RuleCheck) obj).getId())
                .build();
    }
}
