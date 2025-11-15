package page.admin;

import org.openqa.selenium.By;
import utils.Driver;

public class AllRoomTypesPage extends HomePage {
    private final By searchLocator = By.cssSelector("[type=\"search\"]");
    private final By firstResultTitleLocator = By.cssSelector("table tbody tr:first-child td.center.sorting_1"); // change to //table/tbody//tr[1]/td[1]

    public void searchRoomType(String room) {
        Driver.getDriver().findElement(searchLocator).sendKeys(room);
    }

    public String getSearchResult() {
        return Driver.getDriver().findElement(firstResultTitleLocator).getText();
    }
}
