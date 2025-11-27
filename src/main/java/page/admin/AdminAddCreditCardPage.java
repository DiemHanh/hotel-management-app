package page.admin;

import io.qameta.allure.Step;
import models.admin.AdminCreditCard;
import org.openqa.selenium.By;
import utils.Driver;

public class AdminAddCreditCardPage {
    private final By creditCardNumberLocator = By.id("number");
    private final By ownerNameLocator = By.id("ownerName");
    private final By expiryMonthLocator = By.id("list1");
    private final By expiryYearLocator = By.id("expiryYear");
    private final By cvvCodeLocator = By.id("cvvcode");
    private final By balanceLocator = By.id("balance");
    private final By submitBtnLocator = By.cssSelector("[type=\"submit\"]");

    @Step("Add a Credit Card")
    public void addCreditCard(AdminCreditCard adminCreditCard) {
        enterCreditCardNumber(adminCreditCard.getCreditCardNumber());
        enterOwnerName(adminCreditCard.getOwnerName());
        enterExpiryMonth(adminCreditCard.getExpiryMonth());
        enterExpiryYear(adminCreditCard.getExpiryYear());
        enterCvvCode(adminCreditCard.getCvvCode());
        enterBalance(adminCreditCard.getBalance());
        clickSubmitButton();
    }

    @Step("Enter Credit Card Number: {0}")
    public void enterCreditCardNumber(String creditCardNumber) {
        Driver.getDriver().findElement(creditCardNumberLocator).sendKeys(creditCardNumber);
    }

    @Step("Enter Card Owner Name: {0}")
    public void enterOwnerName(String ownerName) {
        Driver.getDriver().findElement(ownerNameLocator).sendKeys(ownerName);
    }

    @Step("Enter Expiry Month: {0}")
    public void enterExpiryMonth(int expiryMonth) {
        Driver.getDriver().findElement(expiryMonthLocator).clear();
        Driver.getDriver().findElement(expiryMonthLocator).sendKeys(String.valueOf(expiryMonth));
    }

    @Step("Enter Expiry Year: {0}")
    public void enterExpiryYear(int expiryYear) {
        Driver.getDriver().findElement(expiryYearLocator).clear();
        Driver.getDriver().findElement(expiryYearLocator).sendKeys(String.valueOf(expiryYear));
    }

    @Step("Enter CVV Code: {0}")
    public void enterCvvCode(String cvvCode) {
        Driver.getDriver().findElement(cvvCodeLocator).clear();
        Driver.getDriver().findElement(cvvCodeLocator).sendKeys(cvvCode);
    }

    @Step("Enter Card Balance: {0}")
    public void enterBalance(double balance) {
        Driver.getDriver().findElement(balanceLocator).clear();
        Driver.getDriver().findElement(balanceLocator).sendKeys(String.valueOf(balance));
    }

    @Step("Click Submit Button")
    public void clickSubmitButton() {
        Driver.getDriver().findElement(submitBtnLocator).click();
    }
}
