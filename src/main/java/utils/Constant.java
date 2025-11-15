package utils;

import models.AccountSystem;
import models.user.CreditCard;

public class Constant {
    public final static CreditCard DEFAULT_CREDIT_CARD = new CreditCard(
            "2222 3333 4444 5555",
            "JOHN HENRY",
            "1225",
            "123"
    );

    public final static AccountSystem DEFAULT_ACCOUNT_ADMIN = new AccountSystem(
            "admin",
            "123456"
    );

    public final static AccountSystem DEFAULT_ACCOUNT_USER = new AccountSystem(
            "nhi123",
            "nhi123"
    );
}
