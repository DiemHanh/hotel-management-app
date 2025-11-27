package page.admin;

import io.qameta.allure.Step;
import models.admin.AdminCreditCard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.CreditCardTable;
import utils.Driver;

import java.util.List;
import java.util.stream.Collectors;

public class AdminViewAllCreditCardPage extends AdminHomePage {
    private final By searchLocator = By.cssSelector("[type=\"search\"]");
    private final By headerLocator = By.xpath("//table/thead//th");
    private final By firstRowLocator = By.xpath("//table/tbody/tr[1]");

    private String addSpacesEvery4Digits(String digits) {
        return digits.replaceAll("(.{4})(?=.)", "$1 ");
    }

    @Step("Search credit card: {0}")
    public void searchCreditCard(String creditCardDigits) {
        WebElement search = Driver.getWebDriverWait()
                .until(ExpectedConditions.elementToBeClickable(searchLocator));
        search.clear();
        search.sendKeys(addSpacesEvery4Digits(creditCardDigits));
        Driver.getWebDriverWait()
                .until(ExpectedConditions.visibilityOfElementLocated(firstRowLocator));
    }

    @Step("Get cell locator at row {0}, column {1}")
    public By getCellLocator(int row, int col) {
        String cellXpath = String.format("//table/tbody/tr[%d]/td[%d]", row, col);
        return By.xpath(cellXpath);
    }

    private String getSearchResult(CreditCardTable column, int compareRow) {
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

    @Step("Get credit card number at row {0}")
    public String getCreditCardNumberByRowIndex(int row) {
        return getSearchResult(CreditCardTable.CREDIT_CARD_NUMBER, row).replace(" ", "");
    }

    @Step("Get card owner name at row {0}")
    public String getOwnerNameByRowIndex(int row) {
        return getSearchResult(CreditCardTable.OWNER_NAME, row);
    }

    @Step("Get expiry month at row {0}")
    public int getExpiryMonthByRowIndex(int row) {
        String[] parts = getSearchResult(CreditCardTable.EXPIRY_MONTH, row)
                .split("/", 2);
        return Integer.parseInt(parts[0].trim());
    }

    @Step("Get expiry year at row {0}")
    public int getExpiryYearByRowIndex(int row) {
        String[] parts = getSearchResult(CreditCardTable.EXPIRY_YEAR, row)
                .split("/", 2);
        return Integer.parseInt(parts[1].trim());
    }

    @Step("Get balance at row {0}")
    public double getBalanceInRow(int row) {
        return Double.parseDouble(getSearchResult(CreditCardTable.BALANCE, row));
    }

    @Step("Get full credit card data at row {0}")
    public AdminCreditCard getCreditCardByIndex(int row) {
        AdminCreditCard c = new AdminCreditCard(
                getCreditCardNumberByRowIndex(row),
                getOwnerNameByRowIndex(row),
                getExpiryMonthByRowIndex(row),
                getExpiryYearByRowIndex(row),
                "",
                getBalanceInRow(row)
        );
        return c;
    }
}
