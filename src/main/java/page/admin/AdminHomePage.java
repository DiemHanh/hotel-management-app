package page.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Driver;

public class AdminHomePage {
    private final By roomTypesLabelLocator = By.xpath("//span[text()='Room Types']");
    private final By addRoomTypeLocator = By.xpath("//a[@href='/admin/room-type/create']"); // change: Add Room Type >> text
    private final By roomsLabelLocator = By.xpath("//span[@class='title' and normalize-space()='Rooms']\n");
    private final By addRoomLocator = By.cssSelector("a.nav-link[href='/admin/rooms/create'] .title");
    private final By logOutButtonLocator = By.cssSelector("[data-original-title=\"Logout\"]");

    public void expandRoomTypesSection() { // rename
        // recheck is expand or not
        Driver.getDriver().findElement(roomTypesLabelLocator).click();
        Driver.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(addRoomTypeLocator));
    }

    public void clickAddRoomType() {
        Driver.getDriver().findElement(addRoomTypeLocator).click();
    }

    public void expandRoomsSection() {// rename
        Driver.getDriver().findElement(roomsLabelLocator).click();
        Driver.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(addRoomLocator));
    }

    public void clickAddRoom() {
        Driver.getDriver().findElement(addRoomLocator).click();
    }

    public void clickLogOutButton() {
        Driver.getDriver().findElement(logOutButtonLocator).click();
    }
}
