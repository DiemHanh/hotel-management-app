package utils;

import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;

public class TabWindow {
    public void switchBackToOriginalTab() {
        ArrayList<String> tabs = new ArrayList<>(Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(tabs.get(0));
    }

    public void switchToLastTab() {
        ArrayList<String> tabs = new ArrayList<>(Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(tabs.get(tabs.size() - 1));
    }

    public final long getCurrentScrollPosition() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        return (long) js.executeScript("return window.pageYOffset;");
    }

    public void scrollToPageBottom() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollTo({top: document.documentElement.scrollHeight, behavior: 'smooth'});");
    }
}
