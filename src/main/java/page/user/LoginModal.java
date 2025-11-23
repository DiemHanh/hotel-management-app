package page.user;

import io.qameta.allure.Step;
import models.Account;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Driver;

public class LoginModal {
    private final By usernameLocator = By.name("email");
    private final By passwordLocator = By.name("password");
    private final By loginButtonLocator = By.cssSelector("[value='Sign In']");
    private final By loginSignupModalLocator = By.id("login_signup");

    public void login(Account account) {
        enterUsername(account.getUsername());
        enterPassword(account.getPassword());
        clickLoginButton();

        // wait until login success and close dialog
        Driver.getWebDriverWait().until(ExpectedConditions.invisibilityOfElementLocated(loginSignupModalLocator));
    }

    @Step("enter username: {0}")
    public void enterUsername(String username) {
        Driver.getDriver().findElement(usernameLocator).sendKeys(username);
    }

    @Step("enter password: {0}")
    public void enterPassword(String password) {
        Driver.getDriver().findElement(passwordLocator).sendKeys(password);
    }

    public void clickLoginButton() {
        Driver.getDriver().findElement(loginButtonLocator).click();
    }
}
