package userPages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Driver;

public class HomePage extends LoginModal {
    private final By loginBtnLocator = By.xpath("//header//a[@href='#Login_tab']");
    private final By loginSignupModalLocator = By.id("login_signup");

    private final By checkInInputLocator = By.id("check-in");
    private final By checkOutInputLocator = By.id("check-out");
    private final By adultInputLocator = By.name("adult");
    private final By childrenInputLocator = By.name("children");
    private final By searchBtnLocator = By.cssSelector("[value='Search']");
    private final By roomLinkLocator = By.cssSelector("[href='/rooms']");

    public void openLoginModal() {
        Driver.getDriver().findElement(loginBtnLocator).click();
        Driver.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(loginSignupModalLocator));
    }

    public void searchBookingAvailable(String checkIndate, String checkOutdate, String adult, String child) {
        enterCheckInDate(checkIndate);
        enterCheckOutDate(checkOutdate);
        enterNumberAdult(adult);
        enterNumberChild(child);
        clickSearchBtn();
    }

    public void enterCheckInDate(String checkIndate) {
        Driver.getDriver().findElement(checkInInputLocator).sendKeys(checkIndate);
    }

    public void enterCheckOutDate(String checkOutdate) {
        Driver.getDriver().findElement(checkOutInputLocator).sendKeys(checkOutdate);
    }

    public void enterNumberAdult(String adult) {
        Driver.getDriver().findElement(adultInputLocator).sendKeys(adult);
    }

    public void enterNumberChild(String child) {
        Driver.getDriver().findElement(childrenInputLocator).sendKeys(child);
    }

    public void clickSearchBtn() {
        Driver.getDriver().findElement(searchBtnLocator).click();
    }

    public void openRoomsPage() {
        Driver.getDriver().findElement(roomLinkLocator).click();
    }
}
