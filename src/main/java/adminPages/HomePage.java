package adminPages;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import utils.Driver;
public class HomePage {
    private final By RoomTypesLabelLocator = By.xpath("//span[text()='Room Types']");
    private final By AddRoomTypeLocator = By.xpath("//a[@href='/admin/room-type/create']");

    public void expandRoomTypesSection() {
        Driver.getDriver().findElement(RoomTypesLabelLocator).click();
        Driver.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(AddRoomTypeLocator));
    }

    public void clickAddRoomType() {
        Driver.getDriver().findElement(AddRoomTypeLocator).click();
    }
}
