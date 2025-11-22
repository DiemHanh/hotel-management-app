package page.admin;

import models.admin.AdminCreditCard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.CreditCardTable;
import utils.Driver;

import java.util.List;
import java.util.stream.Collectors;

public class AdminViewAllCreditCardPage extends AdminHomePage {
    private static final String COL_EXPIRY_DATE = "Expiry Date";
    private final By searchLocator = By.cssSelector("[type=\"search\"]");
    private final By headerLocator = By.xpath("//table/thead//th");
    private final By firstRowLocator = By.xpath("//table/tbody/tr[1]");

    private String addSpacesEvery4Digits(String digits) {
        return digits.replaceAll("(.{4})(?=.)", "$1 ");
    }

    public void searchCreditCard(String creditCardDigits) {
        WebElement search = Driver.getWebDriverWait()
                .until(ExpectedConditions.elementToBeClickable(searchLocator));
        search.clear();
        search.sendKeys(addSpacesEvery4Digits(creditCardDigits));
        Driver.getWebDriverWait()
                .until(ExpectedConditions.visibilityOfElementLocated(firstRowLocator));
    }

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

    public String getCreditCardNumberByRowIndex(int row) {
        return getSearchResult(CreditCardTable.CREDIT_CARD_NUMBER, row).replace(" ", "");
    }

    public String getOwnerNameByRowIndex(int row) {
        return getSearchResult(CreditCardTable.OWNER_NAME, row);
    }

    public int getExpiryMonthByRowIndex(int row) {
        String[] parts = getSearchResult(CreditCardTable.EXPIRY_MONTH, row)
                .split("/", 2);
        return Integer.parseInt(parts[0].trim());
    }

    public int getExpiryYearByRowIndex(int row) {
        String[] parts = getSearchResult(CreditCardTable.EXPIRY_YEAR, row)
                .split("/", 2);
        return Integer.parseInt(parts[1].trim());
    }

    public double getBalanceInRow(int row) {
        return Double.parseDouble(getSearchResult(CreditCardTable.BALANCE, row));
    }

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


