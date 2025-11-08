package adminPages;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Driver;

public class AddRoomTypePage {
    private final By TitleLocator = By.id("txtRoomName");
    private final By PriceLocator = By.name("price");
    private final By AdultCapacityLocator = By.id("list2");
    private final By AdultDropdownLocator = By.cssSelector("[data-mdl-for=list2]");
    private final By visibleMenuAdultLocator = By.cssSelector("[class='mdl-menu__container is-upgraded is-visible']");
    private final By Children


    public void enterTitle() {
        Driver.getDriver().findElement(TitleLocator).sendKeys();
    }
    public void enterPrice() {
        Driver.getDriver().findElement(PriceLocator).sendKeys();
    }

    public void openAdultCapacityMenu() {
        Driver.getDriver().findElement(AdultCapacityLocator).click();
    }

    public void selectAdultCapacity(int number) throws InterruptedException {
        Driver.getDriver().findElement(AdultDropdownLocator);
        Driver.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(visibleMenuAdultLocator));
        Driver.getDriver().findElement(By.xpath("//ul[@data-mdl-for='list2']//li[@data-val='" + number + "']")).click();

    }
}
