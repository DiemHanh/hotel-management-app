package models.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdminCreditCard {
    private String creditCardNumber;
    private String ownerName;
    private int expiryMonth;
    private int expiryYear;
    private String cvvCode;
    private double balance;

    //use toString because not allowed to compare 2 objects
    @Override
    public String toString() {
        return "AdminCreditCard{" +
                "creditCardNumber='" + creditCardNumber + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", expiryMonth=" + expiryMonth +
                ", expiryYear=" + expiryYear +
                ", balance=" + balance +
                '}';
    }
}
