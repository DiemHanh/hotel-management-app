package userPages;

import models.user.Payment;
import org.openqa.selenium.By;
import utils.Driver;

public class PaymentPage {
    private final By cardNumberInputLocator = By.id("cardNumber");
    private final By nameInputLocator = By.id("ownerName");
    private final By expiryInputLocator = By.name("expiry");
    private final By cvvInputLocator = By.id("cvvcode");
    private final By payNowBtnLocator = By.xpath("//div[@id='pills-creadit']//input[@value='Pay Now']");

    public void enterPaymentBooking(Payment payment) {
        enterCardNumber(payment.getCardNumber());
        enterName(payment.getCardName());
        enterExpiry(payment.getCardDate());
        enterCVV(payment.getCardCVV());
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

    public void enterCVV(int cvv) {
        Driver.getDriver().findElement(cvvInputLocator).sendKeys(String.valueOf(cvv));
    }

    public void clickPayNowBtn() {
        Driver.getDriver().findElement(payNowBtnLocator).click();
    }
}
