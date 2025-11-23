package page.user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Driver;

import java.util.List;
import java.util.stream.Collectors;

public class MyBookingPage {
    private final By modalCancelLocator = By.id("cancel-booking");
    private final By bookingNumberLocator = By.className("widget_ratting");
    private final By cancelBookingLocator = By.cssSelector(".btn.btn-danger");
    private final By cancelBtnLocator = By.xpath("//div[@class='modal-footer text-right border-0']//input[@class='btn btn-success']");

    public int getIndexFromBookingNumber(String bookingNumber) {
        List<String> bookingNumbers = Driver.getDriver().findElements(bookingNumberLocator)
                .stream()
                .map(WebElement::getText)
                .map(String::trim)
                .map(text -> text.replaceAll(".*#", "")) // Remove "Booking number: #" prefix
                .collect(Collectors.toList());
        return bookingNumbers.indexOf(bookingNumber);
    }

    public void openCancelModalByIndex(int index) {
        List<WebElement> cancelButtons = Driver.getDriver().findElements(cancelBookingLocator);
        cancelButtons.get(index).click();
    }

    public void cancelBooking() {
        Driver.getWebDriverWait()
                .until(ExpectedConditions.visibilityOfElementLocated(modalCancelLocator));
        Driver.getDriver().findElement(cancelBtnLocator).click();
    }
}
