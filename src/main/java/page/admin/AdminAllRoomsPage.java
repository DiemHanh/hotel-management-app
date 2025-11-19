package page.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Driver;

import java.util.List;
import java.util.stream.Collectors;

public class AdminAllRoomsPage {
    private final By searchLocator = By.cssSelector("input[type='search']");
    private final By headerLocator = By.xpath("//table/thead//th");

    public void searchRoom(String room) {
        Driver.getDriver().findElement(searchLocator).sendKeys(room);
    }

    public String getSearchResult(String columnName, int compareRow) {
        // get all header tags
        List<WebElement> columns = Driver.getDriver().findElements(headerLocator);

        // list all title of header
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
        String cellXpath = "//table/tbody/tr[" + compareRow + "]/td[" + colIdx + "]"; // use $s    >> public By getLocator...
        WebElement cell = Driver.getDriver().findElement(By.xpath(cellXpath));
        return cell.getText().trim();
    }

    public boolean getStatusAsBoolean(int compareRow) {
        String uiStatus = getSearchResult("Status", compareRow);

        if (uiStatus.equalsIgnoreCase("Active") || uiStatus.equalsIgnoreCase("Inactive")) {
            return uiStatus.equalsIgnoreCase("Active");
        } else if (uiStatus.equals("1") || uiStatus.equals("0")) {
            return uiStatus.equals("1");
        } else {
            throw new RuntimeException("Unknown Status format on UI: " + uiStatus);
        }

        // return uiStatus.equals("1") || uiStatus.equalsIgnoreCase("Active") || equalIgnoreCase("Active)
    }
}


