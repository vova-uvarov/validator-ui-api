package ru.openbbank.documentvalidator.repository.model;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import ru.openbbank.documentvalidator.repository.model.enums.DocumentType;
import ru.openbbank.documentvalidator.repository.model.enums.FieldRule;

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
public class DocumentRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    DocumentType documentType;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "documentTypeId", insertable = false, updatable = false)
    Set<FieldRule> fieldRules;


    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .build();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DocumentRule)) {
            return false;
        }

        return new EqualsBuilder()
                .append(id, ((DocumentRule) obj).getId())
                .build();
    }
}
