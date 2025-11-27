package page.admin;

import io.qameta.allure.Step;
import models.Account;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Driver;

public class AdminLoginPage {
    private final By userNameAdminLocator = By.name("username");
    private final By passwordAdminLocator = By.name("password");
    private final By loginButtonLocator = By.className("login100-form-btn");
    private final By loginPageLocator = By.className("wrap-login100");

    @Step("Login as: {0}")
    public void login(Account account) {
        enterUserName(account.getUsername());
        enterPassword(account.getPassword());
        clickLoginBtn();
        Driver.getWebDriverWait().until(ExpectedConditions.invisibilityOfElementLocated(loginPageLocator));
    }

    @Step("Enter username: {0}")
    public void enterUserName(String username) {
        Driver.getDriver().findElement(userNameAdminLocator).sendKeys(username);
    }

    @Step("Enter password: {0}")
    public void enterPassword(String password) {
        Driver.getDriver().findElement(passwordAdminLocator).sendKeys(password);
    }

    @Step("Click Login button")
    public void clickLoginBtn() {
        Driver.getDriver().findElement(loginButtonLocator).click();
    }
}
