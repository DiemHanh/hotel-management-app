package page.admin;

import io.qameta.allure.Step;
import models.admin.Room;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Driver;

public class AdminAddRoomPage {
    private final By roomNumberLocator = By.id("txtRoomNo");
    private final By roomTypeLocator = By.id("list1");
    private final By roomTypeListLocator = By.cssSelector("[data-mdl-for='list1'] li.mdl-menu__item:not([disabled])");
    private final By visibleRoomTypeLocator = By.cssSelector("[class=\"mdl-menu__container is-upgraded is-visible\"]");
    private final By floorLocator = By.id("floor");
    private final By statusLocator = By.xpath("//input[@name='status']");
    private final By sliderLocator = By.xpath("//label[@class='switchToggle']");
    private final By descriptionLocator = By.id("education");
    private final By submitButtonLocator = By.cssSelector("[type=\"submit\"]");

    @Step("Add room: {0}")
    public void addRoom(Room room) {
        enterRoomNumber(room.getRoomNumber());

        String selectedType = selectLatestRoomType();
        room.setRoomType(selectedType);

        enterFloor(room.getFloor());

        boolean selectedStatus = activateStatus();
        room.setStatus(selectedStatus);

        enterDescription(room.getDescription());
        clickSubmitButton();
    }

    @Step("Enter room number: {0}")
    public void enterRoomNumber(int roomNumber) {
        Driver.getDriver().findElement(roomNumberLocator).sendKeys(String.valueOf(roomNumber));
    }

    @Step("Select latest room type")
    public String selectLatestRoomType() {
        Driver.getDriver().findElement(roomTypeLocator).click();
        Driver.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(visibleRoomTypeLocator));

        var items = Driver.getDriver().findElements(roomTypeListLocator);
        WebElement lastItem = items.get(items.size() - 1);
        Driver.getWebDriverWait().until(driver -> !lastItem.getText().trim().isEmpty());
        String selectedType = lastItem.getText().trim();
        lastItem.click();
        return selectedType;
    }

    @Step("Enter floor: {0}")
    public void enterFloor(int floor) {
        Driver.getDriver().findElement(floorLocator).clear();
        Driver.getDriver().findElement(floorLocator).sendKeys(String.valueOf(floor));

    }

    @Step("Activate status if not selected")
    public boolean activateStatus() {
        WebElement input = Driver.getDriver().findElement(statusLocator);
        if (!input.isSelected()) {
            Driver.getDriver().findElement(sliderLocator).click();
        }
        return Driver.getDriver().findElement(statusLocator).isSelected();
    }

    @Step("Enter description")
    public void enterDescription(String description) {
        Driver.getDriver().findElement(descriptionLocator).sendKeys(description);
    }

    @Step("Submit room")
    public void clickSubmitButton() {
        Driver.getDriver().findElement(submitButtonLocator).click();
    }
}
