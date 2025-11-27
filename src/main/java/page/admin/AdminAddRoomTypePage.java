package page.admin;

import io.qameta.allure.Step;
import models.admin.RoomType;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Driver;

public class AdminAddRoomTypePage {
    private final By titleLocator = By.id("txtRoomName");
    private final By priceLocator = By.name("price");
    private final By adultCapacityLocator = By.id("list2");
    private final By visibleMenuAdultLocator = By.cssSelector("[class='mdl-menu__container is-upgraded is-visible']");
    private final By childrenCapacityLocator = By.id("list3");
    private final By visibleMenuChildrenLocator = By.cssSelector(".mdl-menu__container.is-upgraded.is-visible");
    private final By descriptionLocator = By.id("text7");
    private final By submitLocator = By.cssSelector("[class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect m-b-10 m-r-20 btn-pink\"]");

    @Step("Add room type")
    public void addRoomType(RoomType roomType) {
        enterTitle(roomType.getTitle());
        enterPrice(roomType.getPrice());
        selectAdultCapacity(roomType.getAdultCapacity());
        selectChildrenCapacity(roomType.getChildrenCapacity());
        enterDescription(roomType.getDescription());
        clickSubmitButton();
    }

    @Step("Enter title: {0}")
    public void enterTitle(String title) {
        Driver.getDriver().findElement(titleLocator).sendKeys(title);
    }

    @Step("Enter price: {0}")
    public void enterPrice(double price) {
        Driver.getDriver().findElement(priceLocator).sendKeys(String.valueOf(price));
    }

    @Step("Select adult capacity: {0}")
    public void selectAdultCapacity(int number) {
        Driver.getDriver().findElement(adultCapacityLocator).click();
        Driver.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(visibleMenuAdultLocator));
        Driver.getDriver().findElement(By.xpath("//ul[@data-mdl-for='list2']//li[@data-val='" + number + "']")).click();
    }

    @Step("Select children capacity: {0}")
    public void selectChildrenCapacity(int number) {
        Driver.getDriver().findElement(childrenCapacityLocator).click();
        Driver.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(visibleMenuChildrenLocator));
        Driver.getDriver().findElement(By.xpath("//ul[@data-mdl-for='list3']//li[@data-val='" + number + "']")).click();
    }

    @Step("Enter description")
    public void enterDescription(String description) {
        Driver.getDriver().findElement(descriptionLocator).sendKeys(description);
    }

    @Step("Submit room type form")
    public void clickSubmitButton() {
        Driver.getDriver().findElement(submitLocator).click();
    }
}
