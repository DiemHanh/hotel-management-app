package models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {// CreditCard
    private String cardNumber;
    private String cardName;
    private String cardDate; //YearMonth/MonthYear
    private int cardCVV;// String
}
