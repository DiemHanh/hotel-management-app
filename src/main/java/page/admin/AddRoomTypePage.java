package page.admin;
import models.admin.RoomType;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Driver;

public class AddRoomTypePage {
    private final By TitleLocator = By.id("txtRoomName");
    private final By PriceLocator = By.name("price");
    private final By AdultCapacityLocator = By.id("list2");
    private final By visibleMenuAdultLocator = By.cssSelector("[class='mdl-menu__container is-upgraded is-visible']");
    private final By ChildrenCapacityLocator = By.id("list3");
    private final By visibleMenuChildrenLocator = By.cssSelector(".mdl-menu__container.is-upgraded.is-visible");
    private final By DescriptionLocator = By.id("text7");
    private final By SubmitLocator = By.cssSelector("[class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect m-b-10 m-r-20 btn-pink\"]");
// rename locator
    public void addRoomType(RoomType roomType) {
        enterTitle(roomType.getTitle());
        enterPrice(roomType.getPrice());
        selectAdultCapacity(roomType.getAdultCapacity());
        selectChildrenCapacity(roomType.getChildrenCapacity());
        enterDescription(roomType.getDescription());
        clickSubmitButton();
    }

    public void enterTitle(String title) {
        Driver.getDriver().findElement(TitleLocator).sendKeys(title);
    }

    public void enterPrice(int price) {
        Driver.getDriver().findElement(PriceLocator).sendKeys(String.valueOf(price));
    }

    public void selectAdultCapacity(int number) {
        Driver.getDriver().findElement(AdultCapacityLocator).click();
        Driver.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(visibleMenuAdultLocator));
        Driver.getDriver().findElement(By.xpath("//ul[@data-mdl-for='list2']//li[@data-val='" + number + "']")).click();
    }

    public void selectChildrenCapacity(int number) {
        Driver.getDriver().findElement(ChildrenCapacityLocator).click();
        Driver.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(visibleMenuChildrenLocator));
        Driver.getDriver().findElement(By.xpath("//ul[@data-mdl-for='list3']//li[@data-val='" + number + "']")).click();

    }

    public void enterDescription(String description) {
        Driver.getDriver().findElement(DescriptionLocator).sendKeys(description);
    }

    public void clickSubmitButton() {
        Driver.getDriver().findElement(SubmitLocator).click();
    }
}
