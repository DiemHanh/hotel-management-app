package models.admin;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminCreditCard {
    private String creditCardNumber;
    private String ownerName;
    private int expiryMonth;
    private int expiryYear;
    private String cvvCode;
    private double balance;

    //use toString because should not compare 2 objects
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
