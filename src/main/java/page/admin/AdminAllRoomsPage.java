package page.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Driver;

import java.util.List;
import java.util.stream.Collectors;

public class AdminAllRoomsPage {
    private final By searchLocator = By.cssSelector("input[type='search']");
    private final By headerLocator = By.xpath("//table/thead//th");
    private final int latestRow = 1;

    public void searchRoom(String room) {
        Driver.getDriver().findElement(searchLocator).sendKeys(room);
    }

    public String getSearchResult(String columnName) {

        // get all header
        List<WebElement> columns = Driver.getDriver().findElements(headerLocator);

        // Map text header -> list string
        List<String> columnTexts = columns.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());

        // get column index
        int colIdx = columnTexts.indexOf(columnName.trim()) + 1;
        if (colIdx == 0) {
            throw new RuntimeException("Column not found: " + columnName
                    + " | Headers = " + columnTexts);
        }
        // get cell by latest row
        String cellXpath = "//table/tbody/tr[" + latestRow + "]/td[" + colIdx + "]";
        WebElement cell = Driver.getDriver().findElement(By.xpath(cellXpath));
        return cell.getText().trim();
    }
    public boolean getStatusAsBoolean() {
        String uiStatus = getSearchResult("Status");

        if (uiStatus.equalsIgnoreCase("Active") || uiStatus.equalsIgnoreCase("Inactive")) {
            return uiStatus.equalsIgnoreCase("Active");
        } else if (uiStatus.equals("1") || uiStatus.equals("0")) {
            return uiStatus.equals("1");
        } else {
            throw new RuntimeException("Unknown Status format on UI: " + uiStatus);
        }
    }

}


