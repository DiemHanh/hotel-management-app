package page.admin;
import models.AccountSystem;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Driver;

public class LoginPage {
    private final By UserNameAdminLocator = By.name("username");
    private final By PasswordAdminLocator = By.name("password");
    private final By LoginButtonLocator = By.className("login100-form-btn");
    private final By LoginPageLocator = By.className("wrap-login100");
// rename locator
    public void login(AccountSystem accountSystem) {
        enterUserName(accountSystem.getUsername());
        enterPassword(accountSystem.getPassword());
        ClickLogInBtn();
        Driver.getWebDriverWait().until(ExpectedConditions.invisibilityOfElementLocated(LoginPageLocator));
    }

    public void enterUserName(String username) {
        Driver.getDriver().findElement(UserNameAdminLocator).sendKeys(username);
    }

    public void enterPassword(String password) {
        Driver.getDriver().findElement(PasswordAdminLocator).sendKeys(password);
    }

    public void ClickLogInBtn() {
        Driver.getDriver().findElement(LoginButtonLocator).click();
    }
}
