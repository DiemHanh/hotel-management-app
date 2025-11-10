package userPages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Config;
import utils.Driver;

import java.util.ArrayList;

public class TempMailPage {
    private final By emailLocator = By.id("email_id");
    private final By latestMessageLocator = By.cssSelector(".messages");
    private final By refreshBtnLocator = By.id("refresh");
    private final By spinnerLocator = By.cssSelector(".pause-spinner");
    private final By subjectEmailLocator = By.cssSelector(".message-content div[class*='text-gray-900']");

    public static void openTempMailPage() {
//        Driver.getDriver().switchTo().newWindow(WindowType.TAB);

        // Open new tab using JavaScript
        ((JavascriptExecutor) Driver.getDriver()).executeScript("window.open()");

        // Switch to the new tab
        ArrayList<String> tabs = new ArrayList<>(Driver.getDriver().getWindowHandles());

        Driver.getDriver().switchTo().window(tabs.get(tabs.size() - 1));
        Driver.getDriver().get(Config.URL_TEMP_MAIL_PAGE);
    }

    public void openLatestEmail() {
        Driver.getDriver().findElement(refreshBtnLocator).click();
        // wait until refresh finishes
        Driver.getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(spinnerLocator));
        Driver.getDriver().findElement(latestMessageLocator).click();
    }

    public String getEmail() {
        openTempMailPage();

        // wait until the email is generated
        Driver.getWebDriverWait().until(ExpectedConditions.not(
                ExpectedConditions.textToBePresentInElementLocated(emailLocator, Driver.getDriver().findElement(emailLocator).getText())
        ));// wait until the email # "generating email"

        return Driver.getDriver().findElement(emailLocator).getText();
    }

    public String getSubjectEmail() {
        return Driver.getDriver().findElement(subjectEmailLocator).getText();
    }
}
