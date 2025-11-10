package adminPages;

import org.openqa.selenium.By;
import utils.Driver;

public class AllRoomsPage {
    private final By searchLocator = By.cssSelector("[type=\"search\"]");
    private final By firstRoomNumberLocator = By.cssSelector("table tbody tr:first-child td.center.sorting_1"); // change

    public void searchRoom(String room) {
        Driver.getDriver().findElement(searchLocator).sendKeys(room);
    }

    public String getSearchResult() {
        return Driver.getDriver().findElement(firstRoomNumberLocator).getText();
    }
}
