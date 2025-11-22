package page.user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Driver;

import java.util.List;
import java.util.stream.Collectors;

public class CancelBookingPage {
    private final By bookingIdLocator = By.cssSelector(".green_text1.float-right");

    public List getListBookingId() {
        return Driver.getDriver().findElements(bookingIdLocator)
                .stream()
                .map(WebElement::getText)
                .map(String::trim)
                .map(text -> text.replaceAll(".*Id:\\s*", "")) // Remove "Id: " prefix
                .collect(Collectors.toList());
    }
}
