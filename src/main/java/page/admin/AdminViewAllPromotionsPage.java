package page.admin;

import io.qameta.allure.Step;
import models.admin.Promotion;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Driver;
import utils.PromotionTable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AdminViewAllPromotionsPage extends AdminHomePage {
    private final By searchLocator = By.cssSelector("[type=\"search\"]");
    private final By headerLocator = By.xpath("//table/thead//th");
    private final By firstRowLocator = By.xpath("//table/tbody/tr[1]");

    @Step("Get promotion name at row {0}")
    public String getPromotionNameByRowIndex(int row) {
        return getSearchResult(PromotionTable.PROMOTION_NAME, row);
    }

    @Step("Get promotion code at row {0}")
    public String getPromotionCodeByRowIndex(int row) {
        return getSearchResult(PromotionTable.PROMOTION_CODE, row);
    }

    @Step("Get period start date at row {0}")
    public String getPeriodStartDateByRowIndex(int row) {
        return getSearchResult(PromotionTable.PERIOD_START_DATE, row);
    }

    @Step("Get period end date at row {0}")
    public String getPeriodEndDateByRowIndex(int row) {
        return getSearchResult(PromotionTable.PERIOD_END_DATE, row);
    }

    @Step("Get promotion type at row {0}")
    public String getPromotionTypeByRowIndex(int row) {
        return getSearchResult(PromotionTable.PROMOTION_TYPE, row);
    }

    @Step("Get promotion value at row {0}")
    public int getPromotionValueByRowIndex(int row) {
        return Integer.parseInt(getSearchResult(PromotionTable.PROMOTION_VALUE, row));
    }

    @Step("Get full promotion data at row {0}")
    public Promotion getPromotionByIndex(int row) {
        Promotion p = new Promotion(
                getPromotionNameByRowIndex(row),
                getPromotionCodeByRowIndex(row),
                getPeriodStartDateByRowIndex(row),
                getPeriodEndDateByRowIndex(row),
                getPromotionTypeByRowIndex(row),
                getPromotionValueByRowIndex(row)
        );
        return p;
    }

    @Step("Search promotion: {0}")
    public void searchPromotion(String promotionName) {
        WebElement search = Driver.getWebDriverWait()
                .until(ExpectedConditions.elementToBeClickable(searchLocator));
        search.clear();
        search.sendKeys(promotionName);
        Driver.getWebDriverWait()
                .until(ExpectedConditions.visibilityOfElementLocated(firstRowLocator));
    }

    @Step("Get cell locator at row {0}, column {1}")
    public By getCellLocator(int row, int col) {
        String cellXpath = String.format("//table/tbody/tr[%d]/td[%d]", row, col);
        return By.xpath(cellXpath);
    }

    private String getSearchResult(PromotionTable column, int compareRow) {
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
