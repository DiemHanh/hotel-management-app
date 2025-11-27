package page.admin;

import io.qameta.allure.Step;
import models.admin.Room;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Driver;
import utils.RoomTable;

import java.util.List;
import java.util.stream.Collectors;

public class AdminAllRoomsPage {
    private final By searchLocator = By.cssSelector("input[type='search']");
    private final By headerLocator = By.xpath("//table/thead//th");
    private final By firstRowLocator = By.xpath("//table/tbody/tr[1]");

    @Step("Search room: {0}")
    public void searchRoom(String room) {
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

    private String getSearchResult(RoomTable column, int compareRow) {
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

    @Step("Get room number at row {0}")
    public int getRoomNumberByRowIndex(int row) {
        return Integer.parseInt(getSearchResult(RoomTable.ROOM_NUMBER, row));
    }

    @Step("Get room type at row {0}")
    public String getRoomTypeByRowIndex(int row) {
        return getSearchResult(RoomTable.ROOM_TYPE, row);
    }

    @Step("Get floor at row {0}")
    public int getFloorByRowIndex(int row) {
        return Integer.parseInt(getSearchResult(RoomTable.FLOOR, row));
    }

    @Step("Get status at row {0}")
    public boolean getStatusAsBoolean(int row) {
        String uiStatus = getSearchResult(RoomTable.STATUS, row);
        return uiStatus.equalsIgnoreCase("Active") || uiStatus.equals("1");
    }

    @Step("Get full room info from row {0}")
    public Room getRoomByIndex(int row) {
        Room r = new Room(
                getRoomNumberByRowIndex(row),
                getRoomTypeByRowIndex(row),
                getFloorByRowIndex(row),
                "",
                getStatusAsBoolean(row));
        return r;
    }
}
