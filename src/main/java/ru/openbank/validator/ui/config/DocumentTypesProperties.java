package ru.openbank.validator.ui.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import ru.openbbank.documentvalidator.repository.model.enums.DocumentType;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "app.dictionary.document")
public class DocumentTypesProperties {
    private Map<DocumentType, String> types;
}
