package userPages;

import org.openqa.selenium.By;
import utils.Driver;

public class ConfirmPage {

    private final By successMess = By.cssSelector(".alert.alert-success");

    public String getSuccessMessage() {
        return Driver.getDriver().findElement(successMess).getText();
    }
}
