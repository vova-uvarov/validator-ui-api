package ru.openbbank.documentvalidator.repository.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static ru.openbbank.documentvalidator.repository.model.enums.DocumentSystem.MARKETPLACE;
import static ru.openbbank.documentvalidator.repository.model.enums.DocumentSystem.NEOMAS;


@AllArgsConstructor
public enum DocumentType {
    /**
     * Сведения по контракту (181-И)
     */
    CONTRACT_INFO("vkinformCon181i", NEOMAS),
    /**
     * Сведения по кредитному договору (181-И)
     */
    CREDIT_INFO("vkinformCred181i", NEOMAS),
    /**
     * Сведения об операциях (181-И)
     */
    OPERATION_INFO("vkdetailInquiry181i", NEOMAS),
    /**
     * Справка о подтверждающих документах
     */
    CONFIRMING_DOCUMENT_INFO("vkconfDocInquiry181i", NEOMAS),
    /**
     * Внесение изменений в раздел 1 ВБК
     */
    MODIFY_INFO("vkmodifyCon181i", NEOMAS),
    /**
     * Завление о снятии с учета контракта
     */
    CLOSE_DOCUMENT_INFO("vkcloseCon181i", NEOMAS),
    /**
     * Исходящие письма
     */
    OUTGOING_EMAIL("freeClientDoc", NEOMAS),
    /**
     * Заявки на депозит
     */
    OVERNIGHT_DEPOSIT("overNightClient", NEOMAS),
    /**
     * Заявки на виртуальный депозит
     */
    VIRTUAL_OVERNIGHT_DEPOSIT("ovrnApplication", NEOMAS),
    /**
     * Отключение/подключение виртуального депозита
     */
    VIRTUAL_OVERNIGHT_DEPOSIT_MODIFY("ovrnContractModify", NEOMAS),
    /**
     * Запрос на отзыв документа
     */
    CANCELLATION_REQUEST("cancellationRequest", NEOMAS),

    /**
     * Распоряжению на списание иностранной валюты с транзитного валютного счета
     */
    CURRENCY_TRANSFER_ORDER("currencyTransferOrders", MARKETPLACE),

    /**
     * Шаблон для распоряжения на списание иностранной валюты с транзитного валютного счета
     */
    CURRENCY_TRANSFER_ORDER_PATTERN("currencyTransferOrderTemplates", MARKETPLACE),
    /**
     * Валютное платежное поручение
     */
    CURRENCY_PAYMENT("currencyPayments", MARKETPLACE),
    /**
     * Покупка/продажа/конверсия валюты
     */
    CONVERSION_DOCUMENT("conversionDocument", MARKETPLACE);

    @Getter
    private String path;

    @Getter
    private DocumentSystem documentSystem;

}
