package page.email;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import utils.Config;
import utils.Driver;

import java.util.ArrayList;

public class YopMail {
    private final By inputEmailLocator = By.id("login");
    private final By clearEmailLocator = By.id("clearbut");
    private final By openInboxBtnLocator = By.id("refreshbut");
    private final By emailLocator = By.className("bname");

    public static void openYopMailPageInNewTab() {
//        Driver.getDriver().switchTo().newWindow(WindowType.TAB);

        // Open new tab using JavaScript
        ((JavascriptExecutor) Driver.getDriver()).executeScript("window.open()");

        // Switch to the new tab
        ArrayList<String> tabs = new ArrayList<>(Driver.getDriver().getWindowHandles());

        Driver.getDriver().switchTo().window(tabs.get(tabs.size() - 1));
        Driver.getDriver().get(Config.URL_YOP_MAIL_PAGE);
    }

    public void openInboxMail(String emailName) {
        Driver.getDriver().findElement(inputEmailLocator).sendKeys(emailName);
        Driver.getDriver().findElement(openInboxBtnLocator).click();
    }

    public String convertToYopMail(String email) {
        return email.split("@")[0] + "@yopmail.com";
    }
}
