package page.admin;
import models.AccountSystem;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Driver;

public class AdminLoginPage {
    private final By userNameAdminLocator = By.name("username");
    private final By passwordAdminLocator = By.name("password");
    private final By loginButtonLocator = By.className("login100-form-btn");
    private final By loginPageLocator = By.className("wrap-login100");

    public void login(AccountSystem accountSystem) {
        enterUserName(accountSystem.getUsername());
        enterPassword(accountSystem.getPassword());
        ClickLogInBtn();
        Driver.getWebDriverWait().until(ExpectedConditions.invisibilityOfElementLocated(loginPageLocator));
    }

    public void enterUserName(String username) {
        Driver.getDriver().findElement(userNameAdminLocator).sendKeys(username);
    }

    public void enterPassword(String password) {
        Driver.getDriver().findElement(passwordAdminLocator).sendKeys(password);
    }

    public void ClickLogInBtn() {
        Driver.getDriver().findElement(loginButtonLocator).click();
    }
}
