package page.email;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import utils.Config;
import utils.Driver;
import utils.TabWindow;

public class YopMail {
    private final By inputEmailLocator = By.id("login");
    private final By clearEmailLocator = By.id("clearbut");
    private final By openInboxBtnLocator = By.id("refreshbut");
    private final By refreshInboxBtnLocator = By.id("refresh");
    private final By emailLocator = By.className("bname");

    public void openYopMailPageInNewTab() {
//        Driver.getDriver().switchTo().newWindow(WindowType.TAB);

        // Open new tab using JavaScript
        ((JavascriptExecutor) Driver.getDriver()).executeScript("window.open()");

        // Switch to the new tab
        TabWindow.switchToLastTab();
        Driver.getDriver().get(Config.URL_YOP_MAIL_PAGE);
    }

    public void openInboxMail(String emailName) {
        Driver.getDriver().findElement(inputEmailLocator).sendKeys(emailName);
        Driver.getDriver().findElement(openInboxBtnLocator).click();
    }

    public void refreshInboxMail() {
        Driver.getDriver().findElement(refreshInboxBtnLocator).click();
//        Driver.getDriver().switchTo().frame()
    }

    public void closeAdvertise() {
//        Driver.getDriver().switchTo().frame()
    }
}
