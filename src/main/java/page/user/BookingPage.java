package page.user;

import models.user.UserInformation;
import org.openqa.selenium.By;
import utils.Driver;

public class BookingPage {
    private final By nameInputLocator = By.id("name");
    private final By emailInputLocator = By.id("email");
    private final By phoneInputLocator = By.id("phone");
    private final By addressInputLocator = By.id("address");
    private final By checkboxLocator = By.xpath("//form[@id='user']//label");
    private final By submitBtnLocator = By.cssSelector("[value='Submit']");

    public void submitUserInfo(UserInformation userInformation) {
        enterName(userInformation.getName());
        enterEmail(userInformation.getEmail());
        enterPhone(userInformation.getPhone());
        enterAddress(userInformation.getAddress());
        checkedBoxBtn();
        clickSubmitButton();
    }

    public void enterName(String username) {
        Driver.getDriver().findElement(nameInputLocator).clear();
        Driver.getDriver().findElement(nameInputLocator).sendKeys(username);
    }

    public void enterEmail(String email) {
        Driver.getDriver().findElement(emailInputLocator).clear();
        Driver.getDriver().findElement(emailInputLocator).sendKeys(email);
    }

    public void enterPhone(String phone) {
        Driver.getDriver().findElement(phoneInputLocator).sendKeys(phone);
    }

    public void enterAddress(String address) {
        Driver.getDriver().findElement(addressInputLocator).sendKeys(address);
    }

    public void clickSubmitButton() {
        Driver.getDriver().findElement(submitBtnLocator).click();
    }

    public void checkedBoxBtn() {
        Driver.getDriver().findElement(checkboxLocator).click();
    }
}
