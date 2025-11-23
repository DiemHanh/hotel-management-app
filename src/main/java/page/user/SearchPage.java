package page.user;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Constant;
import utils.Driver;

public class SearchPage {
    private final By bookingId = By.cssSelector(".green_text1.float-right");

    public String getBookingId() {
        Driver.getWebDriverWait().until(ExpectedConditions.textToBe(Constant.titlePageLocator, "Search"));
        return Driver.getDriver().findElement(bookingId).getText().replaceAll(".*Id:\\s*", "");
    }
}
