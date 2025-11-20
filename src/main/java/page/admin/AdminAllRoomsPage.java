package page.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Driver;

import java.util.List;
import java.util.stream.Collectors;

public class AdminAllRoomsPage {
    private final By searchLocator = By.cssSelector("input[type='search']");
    private final By headerLocator = By.xpath("//table/thead//th");
    private final By firstRowLocator = By.xpath("//table/tbody/tr[1]");

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

    public String getSearchResult(String columnName, int compareRow) {
        Driver.getWebDriverWait()
                .until(ExpectedConditions.visibilityOfElementLocated(firstRowLocator));
        int maxRetry = 3;

        for (int attempt = 1; attempt <= maxRetry; attempt++) {
            try {
                List<WebElement> columns = Driver.getDriver().findElements(headerLocator);
                List<String> columnTexts = columns.stream()
                        .map(WebElement::getText)
                        .map(String::trim)
                        .collect(Collectors.toList());
                int colIdx = columnTexts.indexOf(columnName.trim()) + 1;
                if (colIdx == 0) {
                    throw new RuntimeException(String.format(
                            "Column not found: %s | Headers = %s", columnName, columnTexts));
                }
                WebElement cell = Driver.getDriver().findElement(getCellLocator(compareRow, colIdx));
                return cell.getText().trim();
            } catch (StaleElementReferenceException e) {
                if (attempt == maxRetry) {
                    throw e;
                }
            }
        }
        throw new RuntimeException("Unable to get search result after retries");
    }

    public boolean getStatusAsBoolean(int compareRow) {
        String uiStatus = getSearchResult("Status", compareRow);
        return uiStatus.equalsIgnoreCase("Active") || uiStatus.equals("1");
    }
}
