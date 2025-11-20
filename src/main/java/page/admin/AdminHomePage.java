package page.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Driver;

public class AdminHomePage {
    private final By roomTypesLabelLocator = By.xpath("//span[text()='Room Types']");
    private final By addRoomTypeLocator = By.linkText("Add Room Type");
    private final By roomsLabelLocator = By.xpath("//span[@class='title' and normalize-space()='Rooms']\n");
    private final By addRoomLocator = By.linkText("Add Room");
    private final By logOutButtonLocator = By.cssSelector("[data-original-title=\"Logout\"]");

    public void expandRoomTypesSection() {
        if (Driver.getDriver().findElements(addRoomTypeLocator).isEmpty()) {
            Driver.getDriver().findElement(roomTypesLabelLocator).click();
        }
        Driver.getWebDriverWait()
                .until(ExpectedConditions.visibilityOfElementLocated(addRoomTypeLocator));
    }

    public void clickAddRoomType() {
        Driver.getDriver().findElement(addRoomTypeLocator).click();
    }

    public void expandRoomsSection() {
        if (Driver.getDriver().findElements(addRoomLocator).isEmpty()) {
            Driver.getDriver().findElement(roomsLabelLocator).click();
        }
        Driver.getWebDriverWait()
                .until(ExpectedConditions.visibilityOfElementLocated(addRoomLocator));
    }

    public void navigateToAddRoomDetail() {
        Driver.getDriver().findElement(addRoomLocator).click();
    }

    public void logOut() {
        Driver.getDriver().findElement(logOutButtonLocator).click();
    }
}
