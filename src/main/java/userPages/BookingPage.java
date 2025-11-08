package userPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Driver;

public class BookingPage {
    private final By bookingTitleLocator = By.className("page_title");
    private final By nameInputLocator = By.id("name");
    private final By emailInputLocator = By.id("email");
    private final By phoneInputLocator = By.id("phone");
    private final By addressInputLocator = By.id("address");
    private final By checkboxLocator = By.xpath("//form[@id='user']//label");
    private final By submitBtnLocator = By.cssSelector("[value='Submit']");

    public String getTitleBookingPage() {
        return Driver.getDriver().findElement(bookingTitleLocator).getText();
    }

    public void inputBooking(String name, String email, String phone, String address) {
        enterName(name);
        enterEmail(email);
        enterPhone(phone);
        enterAddress(address);
        checkedBoxBtn();
        clickSubmitButton();
    }

    public void enterName(String username) {
        Driver.getDriver().findElement(nameInputLocator).sendKeys(username);
    }

    public void enterEmail(String email) {
        Driver.getDriver().findElement(emailInputLocator).sendKeys(email);
    }

    public void enterPhone(String phone) {
        Driver.getDriver().findElement(phoneInputLocator).sendKeys(phone);
    }

    public void enterAddress(String address) {
        Driver.getDriver().findElement(addressInputLocator).sendKeys(address);
    }

    public void clickSubmitButton() {
        Driver.getDriver().findElement(submitBtnLocator).click();
    }

    public void checkedBoxBtn() {
        Driver.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(checkboxLocator));
        WebElement checkbox = Driver.getDriver().findElement(checkboxLocator);

        // Only click if not already selected
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }
}
