package adminPages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Driver;

public class HomePage {
    private final By RoomTypesLabelLocator = By.xpath("//span[text()='Room Types']");
    private final By AddRoomTypeLocator = By.xpath("//a[@href='/admin/room-type/create']"); // change: Add Room Type >> text
    private final By RoomsLabelLocator = By.xpath("//span[@class='title' and normalize-space()='Rooms']\n");
    private final By AddRoomLocator = By.cssSelector("a.nav-link[href='/admin/rooms/create'] .title");
    private final By logOutButtonLocator = By.cssSelector("[data-original-title=\"Logout\"]");
// rename locator

    public void expandRoomTypesSection() { // rename
        // recheck is expand or not
        Driver.getDriver().findElement(RoomTypesLabelLocator).click();
        Driver.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(AddRoomTypeLocator));
    }

    public void clickAddRoomType() {
        Driver.getDriver().findElement(AddRoomTypeLocator).click();
    }

    public void expandRoomsSection() {// rename
        Driver.getDriver().findElement(RoomsLabelLocator).click();
        Driver.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(AddRoomLocator));
    }

    public void clickAddRoom() {
        Driver.getDriver().findElement(AddRoomLocator).click();
    }

    public void clickLogOutButton() {
        Driver.getDriver().findElement(logOutButtonLocator).click();
    }
}
