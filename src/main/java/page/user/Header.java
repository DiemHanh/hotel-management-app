package page.user;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Driver;

public class Header {
    private final By loginBtnLocator = By.linkText("Login");
    private final By loginSignupModalLocator = By.id("login_signup");
    private final By userMenuLocator = By.id("NavebarProfileDrop");
    private final By dropdownMenuLocator = By.cssSelector(".dropdown-menu.show");
    private final By myBookingsLinkLocator = By.linkText("My Bookings");
    private final By cancelBookingLinkLocator = By.linkText("Cancel Bookings");

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
}
