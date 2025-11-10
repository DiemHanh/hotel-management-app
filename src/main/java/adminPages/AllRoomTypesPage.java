package adminPages;

import org.openqa.selenium.By;
import utils.Driver;

public class AllRoomTypesPage extends HomePage {
    private final By seachLocator = By.cssSelector("[type=\"search\"]");
    private final By firstResultTitleLocator = By.cssSelector("table tbody tr:first-child td.center.sorting_1");

    public void searchRoomType(String room) {
        Driver.getDriver().findElement(seachLocator).sendKeys(room);
    }

    public String getSearchResult() {
        return Driver.getDriver().findElement(firstResultTitleLocator).getText();}


}
