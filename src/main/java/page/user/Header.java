package page.user;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Driver;

public class Header {
    private final By loginBtnLocator = By.linkText("Login");
    private final By loginSignupModalLocator = By.id("login_signup");
    private final By userMenuLocator = By.id("NavebarProfileDrop");
    private final By dropdownMenuLocator = By.cssSelector(".dropdown-menu.show");
    private final By myBookingsLinkLocator = By.linkText("My Bookings");
    private final By cancelBookingLinkLocator = By.linkText("Cancel Bookings");
    private final By searchBtnLocator = By.className("sb-icon-search");
    private final By searchInputLocator = By.className("sb-search-input");

    @Step("Open login modal")
    public void openLoginModal() {
        Driver.getDriver().findElement(loginBtnLocator).click();
        Driver.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(loginSignupModalLocator));
    }

    public void navigateToMyBookingPage() {
        Driver.getDriver().findElement(userMenuLocator).click();

        // wait until dropdown appear
        Driver.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(dropdownMenuLocator));
        Driver.getDriver().findElement(myBookingsLinkLocator).click();
    }

    public void navigateToCancelBookingPage() {
        Driver.getDriver().findElement(userMenuLocator).click();

        // wait until dropdown appear
        Driver.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(dropdownMenuLocator));
        Driver.getDriver().findElement(cancelBookingLinkLocator).click();
    }

    public void searchBookingNumber(String bookingNumber) {
        Driver.getDriver().findElement(searchBtnLocator).click();
        Driver.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(searchInputLocator));
        Driver.getDriver().findElement(searchInputLocator).sendKeys(bookingNumber, Keys.ENTER);
        // todo: wait search successfully
    }
}
