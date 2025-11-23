package page.email;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import utils.Config;
import utils.Driver;
import utils.TabWindow;

@Slf4j
public class YopMail {
    private final By inputEmailLocator = By.id("login");
    private final By navigateInboxPageLocator = By.id("refreshbut");
    private final By refreshInboxBtnLocator = By.id("refresh");
    private final By emailLocator = By.className("bname");
    private final By subjectEmailLocator = By.cssSelector(".ellipsis.nw.b.f18");
    private final By openInboxDetailBtnLocator = By.className("m");

    private final String inboxId = "ifinbox";
    private final String emailContentId = "ifmail";

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
        Driver.getDriver().findElement(navigateInboxPageLocator).click();
    }

    public void refreshInboxMail() {
        Driver.getDriver().findElement(refreshInboxBtnLocator).click();
    }

    public void openInbox() {
        Driver.switchToIframe(inboxId);
        Driver.getDriver().findElement(openInboxDetailBtnLocator).click();
        Driver.switchBackToDefault();
    }

    public String getSubjectEmail() {
        refreshInboxMail();
        Driver.switchToIframe(emailContentId);
        return Driver.getDriver().findElement(subjectEmailLocator).getText();
    }
}
