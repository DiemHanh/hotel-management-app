package page.user;

import io.qameta.allure.Step;
import models.user.UserInformation;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Driver;

public class BookingPage {
    private final By nameInputLocator = By.id("name");
    private final By emailInputLocator = By.id("email");
    private final By phoneInputLocator = By.id("phone");
    private final By addressInputLocator = By.id("address");
    private final By checkboxLocator = By.xpath("//form[@id='user']//label");
    private final By submitBtnLocator = By.cssSelector("[value='Submit']");
    private final By addPromoLocator = By.xpath("//form[@action=\"addPromo\"]//label");
    private final By promoCodeTextLocator = By.id("code");
    private final By applyButtonLocator = By.xpath("//form[@action=\"addPromo\"]//button");
    private final By subTotalAmountLocator = By.xpath("//td[text()='Sub Total']/following-sibling::td//strong");
    private final By taxAmountLocator = By.xpath("//td[text()='Tax (10%)']/following-sibling::td");
    private final By discountLocator = By.xpath("//td[text()='Discount (You Save)']/following-sibling::td");
    private final By grandTotalLocator = By.xpath("//tr[last()]//td[last()]");

    @Step("Submit user information")
    public void submitUserInfo(UserInformation userInformation) {
        enterName(userInformation.getName());
        enterEmail(userInformation.getEmail());
        enterPhone(userInformation.getPhone());
        enterAddress(userInformation.getAddress());
        checkedBoxBtn();
        clickSubmitButton();
    }

    @Step("enter name: {0}")
    public void enterName(String username) {
        Driver.getDriver().findElement(nameInputLocator).clear();
        Driver.getDriver().findElement(nameInputLocator).sendKeys(username);
    }

    @Step("enter email: {0}")
    public void enterEmail(String email) {
        Driver.getDriver().findElement(emailInputLocator).clear();
        Driver.getDriver().findElement(emailInputLocator).sendKeys(email);
    }

    @Step("enter phone: {0}")
    public void enterPhone(String phone) {
        Driver.getDriver().findElement(phoneInputLocator).sendKeys(phone);
    }

    @Step("enter address: {0}")
    public void enterAddress(String address) {
        Driver.getDriver().findElement(addressInputLocator).sendKeys(address);
    }

    public void clickSubmitButton() {
        Driver.getDriver().findElement(submitBtnLocator).click();
    }

    public void checkedBoxBtn() {
        Driver.getDriver().findElement(checkboxLocator).click();
    }

    public void checkedPromoButton() {
        Driver.getDriver().findElement(addPromoLocator).click();
    }

    public void enterPromoCode(String promoCode) {
        Driver.getDriver().findElement(promoCodeTextLocator).sendKeys(promoCode);
    }

    public void clickApplyButton() {
        Driver.getDriver().findElement(applyButtonLocator).click();
        Driver.getWebDriverWait().until(
                ExpectedConditions.attributeToBe(promoCodeTextLocator, "value", ""));
    }

    public void applyPromoCode(String promoCode) {
        checkedPromoButton();
        enterPromoCode(promoCode);
        clickApplyButton();
    }

    public double getSubTotalAmount() {
        return Double.parseDouble(Driver.getDriver().findElement(subTotalAmountLocator)
                .getText().replace("$", "").trim());
    }

    public double getTaxAmount() {
        return Double.parseDouble(Driver.getDriver().findElement(taxAmountLocator)
                .getText().replace("$", "").trim());
    }

    public double getDiscountAmount() {
        return Double.parseDouble(Driver.getDriver().findElement(discountLocator)
                .getText().replace("$", "").trim());
    }

    public double getGrandTotalAmount() {
        return Double.parseDouble(Driver.getDriver().findElement(grandTotalLocator)
                .getText().replace("$", "").trim());
    }

    public double calculateGrandTotal() {
        return getSubTotalAmount() + getTaxAmount() - getDiscountAmount();
    }
}
