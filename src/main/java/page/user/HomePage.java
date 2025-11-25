package page.user;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Driver;

public class HomePage extends LoginModal {
    private final By scrollToTopLocator = By.id("scrollToTop");
    private final By checkInInputLocator = By.id("check-in");
    private final By checkOutInputLocator = By.id("check-out");
    private final By adultInputLocator = By.name("adult");
    private final By childrenInputLocator = By.name("children");
    private final By searchBtnLocator = By.cssSelector("[value='Search']");
    private final By roomLinkLocator = By.linkText("Rooms");

    public void searchBookingAvailable(String checkIndate, String checkOutdate, String adult, String child) {
        enterCheckInDate(checkIndate);
        enterCheckOutDate(checkOutdate);
        enterNumberAdult(adult);
        enterNumberChild(child);
        clickSearchBtn();
    }

    @Step("Enter check in date: {0}")
    public void enterCheckInDate(String checkIndate) {
        Driver.getDriver().findElement(checkInInputLocator).sendKeys(checkIndate);
    }

    @Step("Enter checkout date: {0}")
    public void enterCheckOutDate(String checkOutdate) {
        Driver.getDriver().findElement(checkOutInputLocator).sendKeys(checkOutdate);
    }

    @Step("Enter number adult: {0}")
    public void enterNumberAdult(String adult) {
        Driver.getDriver().findElement(adultInputLocator).sendKeys(adult);
    }

    @Step("Enter number child: {0}")
    public void enterNumberChild(String child) {
        Driver.getDriver().findElement(childrenInputLocator).sendKeys(child);
    }

    @Step("Click search button")
    public void clickSearchBtn() {
        Driver.getDriver().findElement(searchBtnLocator).click();
    }

    @Step("Navigate to Room page")
    public void openRoomsPage() {
        Driver.getDriver().findElement(roomLinkLocator).click();
    }

    public void scrollToTopPage() {
        Driver.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(scrollToTopLocator)).click();

        // wait until scroll finishes
        Driver.getWebDriverWait().until(ExpectedConditions.invisibilityOfElementLocated(scrollToTopLocator));
    }
}
