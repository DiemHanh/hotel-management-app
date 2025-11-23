package page.user;

import models.user.CreditCard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Constant;
import utils.Driver;

public class PaymentPage {
    private final By cardNumberInputLocator = By.id("cardNumber");
    private final By nameInputLocator = By.id("ownerName");
    private final By expiryInputLocator = By.name("expiry");
    private final By cvvInputLocator = By.id("cvvcode");
    private final By payNowBtnLocator = By.xpath("//div[@id='pills-creadit']//input[@value='Pay Now']");
    private final By balanceMsgLocator = By.className("dic_msg");

    public String getInsufficientBalanceMessage() {
        return Driver.getWebDriverWait()
                .until(ExpectedConditions.visibilityOfElementLocated(balanceMsgLocator))
                .getText()
                .trim();
    }

    public void submitPaymentBooking(CreditCard creditCard) {
        Driver.getWebDriverWait().until(ExpectedConditions.textToBe(Constant.titlePageLocator, "Checkout"));
        enterCardNumber(creditCard.getCardNumber());
        enterName(creditCard.getCardName());
        enterExpiry(creditCard.getCardDate());
        enterCVV(creditCard.getCardCVV());
        clickPayNowBtn();
    }

    public void enterCardNumber(String cardNumber) {
        Driver.getDriver().findElement(cardNumberInputLocator).sendKeys(cardNumber);
    }

    public void enterName(String name) {
        Driver.getDriver().findElement(nameInputLocator).sendKeys(name);
    }

    public void enterExpiry(String date) {
        Driver.getDriver().findElement(expiryInputLocator).sendKeys(date);
    }

    public void enterCVV(String cvv) {
        WebElement cvvInput = Driver.getDriver().findElement(cvvInputLocator);
        cvvInput.clear();
        cvvInput.sendKeys(String.valueOf(cvv));
    }

    public void clickPayNowBtn() {
        Driver.getDriver().findElement(payNowBtnLocator).click();
    }
}
