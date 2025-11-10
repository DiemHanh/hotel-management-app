package adminPages;

import models.admin.Room;
import models.admin.RoomType;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Driver;

import java.security.PublicKey;

public class AddRoomPage {
    private final By RoomNumberLocator = By.id("txtRoomNo");
    private final By RoomTypeLocator = By.id("list1");
    private final By RoomTypeListLocator = By.cssSelector("[data-mdl-for='list1'] li.mdl-menu__item:not([disabled])");
    private final By visibleRoomTypeLocator = By.cssSelector("[class=\"mdl-menu__container is-upgraded is-visible\"]");
    private final By FloorLocator = By.id("floor");
    private final By StatusLocator = By.className("slider");
    private final By DescriptionLocator = By.id("education");
    private final By SubmitButtonLocator = By.cssSelector("[type=\"submit\"]");

    public void addRoom(Room room) {
        enterRoomNumber(room.getRoomNumber());
        selectLatestRoomType();
        enterFloor(room.getFloor());
        setStatus();
        enterDescription(room.getDescription());
    }

    public void enterRoomNumber(int roomnumber) {
        Driver.getDriver().findElement(RoomNumberLocator).sendKeys(String.valueOf(roomnumber));
    }

    public void selectLatestRoomType() {
        Driver.getDriver().findElement(RoomTypeLocator).click();
        Driver.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(visibleRoomTypeLocator));
        var items = Driver.getDriver().findElements(RoomTypeListLocator);
        items.get(items.size() - 1).click();
    }

    public void enterFloor(int floor) {
        Driver.getDriver().findElement(FloorLocator).sendKeys(String.valueOf(floor));
    }

    public void setStatus() {
        Driver.getDriver().findElement(StatusLocator).click();
    }

    public void enterDescription(String description) {
        Driver.getDriver().findElement(DescriptionLocator).sendKeys(description);
    }

    public void clickSubmitButton() {
        Driver.getDriver().findElement(SubmitButtonLocator).click();
    }





}
