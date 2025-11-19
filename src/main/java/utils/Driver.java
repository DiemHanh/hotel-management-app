package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Driver {

    private static final ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return drivers.get();
    }

    public static void setDriver(WebDriver driver) {
        drivers.set(driver);
    }

    public static WebDriverWait getWebDriverWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    }

    public void switchToIframe() {
//        Driver.getDriver().switchTo().frame()
    }

    public void switchBackToDefault() {
//        Driver.getDriver().switchTo().defaultContent())
    }

    public static final long getCurrentScrollPosition() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        return (long) js.executeScript("return window.pageYOffset;");
    }

    public static void scrollToPageBottom() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollTo({top: document.documentElement.scrollHeight, behavior: 'smooth'});");
    }
}
