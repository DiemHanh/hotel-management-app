package page.user;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Constant;
import utils.Driver;

public class ConfirmPage {
    private final By bookingId = By.cssSelector(".green_text1.float-right");
    private final By successMess = By.cssSelector(".alert.alert-success");

    public String getSuccessMessage() {
        Driver.getWebDriverWait().until(ExpectedConditions.textToBe(Constant.titlePageLocator, "Confirm"));
        return Driver.getDriver().findElement(successMess).getText();
    }

    public String getBookingId() {
        Driver.getWebDriverWait().until(ExpectedConditions.textToBe(Constant.titlePageLocator, "Confirm"));
        return Driver.getDriver().findElement(bookingId).getText().replaceAll(".*Id:\\s*", "");
    }
}
