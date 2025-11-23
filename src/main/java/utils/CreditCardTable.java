package utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum CreditCardTable {
    CREDIT_CARD_NUMBER("CreditCard Number"),
    OWNER_NAME("Owner Name"),
    EXPIRY_MONTH("Expiry Date"),
    EXPIRY_YEAR("Expiry Date"),
    BALANCE("Balance");

    private final String value;
}
