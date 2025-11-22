package page.admin;

import models.admin.RoomType;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Driver;
import utils.RoomTypeTable;

import java.util.List;
import java.util.stream.Collectors;

public class AdminAllRoomTypesPageAdmin extends AdminHomePage {
    private final By searchLocator = By.cssSelector("[type=\"search\"]");
    private final By headerLocator = By.xpath("//table/thead//th");
    private final By firstRowLocator = By.xpath("//table/tbody/tr[1]");

    public void searchRoomType(String room) {
        WebElement search = Driver.getWebDriverWait()
                .until(ExpectedConditions.elementToBeClickable(searchLocator));
        search.clear();
        search.sendKeys(room);
        Driver.getWebDriverWait()
                .until(ExpectedConditions.visibilityOfElementLocated(firstRowLocator));
    }

    public By getCellLocator(int row, int col) {
        String cellXpath = String.format("//table/tbody/tr[%d]/td[%d]", row, col);
        return By.xpath(cellXpath);
    }

    public String getTitleByRowIndex(int row) {
        return getSearchResult(RoomTypeTable.TITLE, row);
    }

    public int getAdultCapacityByRowIndex(int row) {
        return Integer.parseInt(getSearchResult(RoomTypeTable.ADULT_CAPACITY, row));
    }

    public int getChildrenCapacityByRowIndex(int row) {
        return Integer.parseInt(getSearchResult(RoomTypeTable.CHILDREN_CAPACITY, row));
    }

    public double getPriceByRowIndex(int row) {
        return Double.parseDouble(getSearchResult(RoomTypeTable.PRICE, row));
    }

    public RoomType getRoomTypeByIndex(int row) {
        RoomType r = new RoomType(
                getTitleByRowIndex(row),
                getPriceByRowIndex(row),
                "",
                getAdultCapacityByRowIndex(row),
                getChildrenCapacityByRowIndex(row)
        );

        return r;
    }

    private String getSearchResult(RoomTypeTable column, int compareRow) {
        Driver.getWebDriverWait()
                .until(ExpectedConditions.visibilityOfElementLocated(firstRowLocator));

        // get header list
        List<String> headers = Driver.getDriver()
                .findElements(headerLocator)
                .stream()
                .map(e -> e.getText().trim())
                .collect(Collectors.toList());

        // get index column
        int colIdx = headers.indexOf(column.getValue()) + 1;
        if (colIdx == 0) {
            throw new IllegalStateException(
                    String.format("Column not found for header: %s", column.getValue())
            );
        }

        // get cell value
        return Driver.getDriver()
                .findElement(getCellLocator(compareRow, colIdx))
                .getText();
    }

}
