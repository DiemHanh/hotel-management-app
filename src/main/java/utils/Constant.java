package utils;

import models.Account;
import models.user.CreditCard;
import org.openqa.selenium.By;

public class Constant {
    public static final By titlePageLocator = By.cssSelector(".page_title");

    public final static CreditCard DEFAULT_CREDIT_CARD = new CreditCard(
            "2222 3333 4444 5555",
            "JOHN HENRY",
            "1225",
            "123"
    );
//    public final static CreditCard DEFAULT_CREDIT_CARD = new CreditCard(
//            "1111222233334444",
//            "CHERRY PHUONG",
//            "1030",
//            "123"
//    );

    public final static Account DEFAULT_ACCOUNT_ADMIN = new Account(
            "admin",
            "123456"
    );

    public final static Account DEFAULT_ACCOUNT_USER = new Account(
            "nhi123",
            "nhi123"
    );
}
