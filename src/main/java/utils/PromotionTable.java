package utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PromotionTable {
    PROMOTION_NAME("Promotion Name"),
    PROMOTION_CODE("Code"),
    PERIOD_START_DATE("Period Start Date"),
    PERIOD_END_DATE("Period End Date"),
    PROMOTION_TYPE("Type"),
    PROMOTION_VALUE("Value");

    private final String value;

}
