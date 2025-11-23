package models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {
    private String cardNumber;
    private String cardName;
    private String cardDate;
    private String cardCVV;
}
