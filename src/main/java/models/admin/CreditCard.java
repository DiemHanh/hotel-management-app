package models.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {
    private String creditCardNumber;
    private String ownerName;
    private int expiryMonth;
    private int expiryYear;
    private String cvvCode;
    private double balance;

    //use toString because not allowed to compare 2 objects
    @Override
    public String toString() {
        return "CreditCard{" +
                "creditCardNumber='" + creditCardNumber + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", expiryMonth=" + expiryMonth +
                ", expiryYear=" + expiryYear +
                ", cvvCode='" + cvvCode + '\'' +
                ", balance=" + balance +
                '}';
    }
}
