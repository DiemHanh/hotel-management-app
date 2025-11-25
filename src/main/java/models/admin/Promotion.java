package models.admin;

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Promotion {
    private String promotionName;
    private String promotionCode;
    private String periodStartDate;
    private String periodEndDate;
    private String promotionType;
    private int promotionValue;

    @Override
    public String toString() {
        return "Promotion{" +
                "promotionName='" + promotionName + '\'' +
                ", promotionCode='" + promotionCode + '\'' +
                ", periodStartDate=" + periodStartDate +
                ", periodEndDate=" + periodEndDate +
                ", promotionType='" + promotionType + '\'' +
                ", promotionValue=" + promotionValue +
                '}';
    }
}
