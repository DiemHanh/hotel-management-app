package page.admin;

import models.admin.Promotion;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.DateUtils;
import utils.Driver;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

public class AdminAddPromotionPage extends DateUtils {

    private final By promotionNameLocator = By.name("promname");
    private final By promotionCodeLocator = By.name("code");
    private final By periodStartDateLocator = By.id("startDate");
    private final By periodEndDateLocator = By.id("endDate");
    private final By promotionTypeLocator = By.id("list3");
    private final By promotionValueLocator = By.id("value");
    private final By nextButtonLocator = By.cssSelector("a.dtp-select-month-after");
    private final By visibleOkButtonLocator = By.cssSelector("button.dtp-btn-ok");
    private final By submitBtnLocator = By.xpath("//button[text()='Submit']");
    private final By promotionTypeDropDownLocator = By.cssSelector("[data-mdl-for=\"list3\"] li");

    public void addPromotion(Promotion promotion, String type) throws InterruptedException {
        enterPromotionName(promotion.getPromotionName());
        enterPromotionCode(promotion.getPromotionCode());
        selectStartDateToday();
        selectEndDateTomorrow();
        openPromotionTypeDropDown(type);
        enterPromotionValue(promotion.getPromotionValue());
        clickSubmitButton();
    }

    public void enterPromotionName(String promotionName) {
        Driver.getDriver().findElement(promotionNameLocator).sendKeys(promotionName);
    }

    public void enterPromotionCode(String promotionCode) {
        Driver.getDriver().findElement(promotionCodeLocator).sendKeys(promotionCode);
    }

    public void enterPromotionValue(int promotionValue) {
        Driver.getDriver().findElement(promotionValueLocator).clear();
        Driver.getDriver().findElement(promotionValueLocator).sendKeys(String.valueOf(promotionValue));
    }

    public void openPromotionTypeDropDown(String promotion) throws InterruptedException {
        Driver.getDriver().findElement(promotionTypeLocator).click();
        selectPromotionTypeFromDropdown(promotion);
    }


    public void selectPromotionTypeFromDropdown(String promotionType) throws InterruptedException {
        Driver.getWebDriverWait().until(
                ExpectedConditions.visibilityOfElementLocated(promotionTypeDropDownLocator)
        );

        List<WebElement> options = Driver.getDriver().findElements(promotionTypeDropDownLocator);
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(promotionType)) {
                option.click();
            }
        }
    }

    public void selectStartDateToday() {
        Driver.getDriver().findElement(periodStartDateLocator).click();
        clickVisibleOkByJs();
    }

    public void selectEndDateTomorrow() {
        Driver.getDriver().findElement(periodEndDateLocator).click();
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        //check month(today) = month(nextday)
        if (tomorrow.getMonth().equals(today.getMonth())) {
            clickDayByJs(tomorrow.getDayOfMonth());
        }
        //tomorrow is in the next month -> go next month, then pick day 1
        else {
            Driver.getWebDriverWait()
                    .until(ExpectedConditions.elementToBeClickable(nextButtonLocator))
                    .click();
            clickDayByJs(1);
        }
        clickVisibleOkByJs();
    }

    public void clickSubmitButton() {
        Driver.getDriver().findElement(submitBtnLocator).click();
    }

    // Return visible & enabled day cell element from the currently opened date picker calendar
    private WebElement getVisibleDayCell(int dayOfMonth) {
        By locator = By.cssSelector(
                String.format("td[data-date='%d'] a.dtp-select-day", dayOfMonth)
        );
        return Driver.getWebDriverWait().until(driver ->
                driver.findElements(locator).stream()
                        .filter(e -> e.isDisplayed() && e.isEnabled())
                        .findFirst()
                        .orElseThrow(() -> new NoSuchElementException(
                                String.format("Visible day cell not found for date: %d", dayOfMonth)
                        ))
        );
    }

    private WebElement getVisibleOkButton() {
        return Driver.getWebDriverWait().until(driver ->
                driver.findElements(visibleOkButtonLocator).stream()
                        .filter(e -> e.isDisplayed() && e.isEnabled())
                        .findFirst()
                        .orElseThrow(() ->
                                new NoSuchElementException("Visible OK button not found in date picker"))
        );
    }

    //Click a visible Ok btn using JavaScript
    private void clickVisibleOkByJs() {
        WebElement okBtn = getVisibleOkButton();
        ((JavascriptExecutor) Driver.getDriver())
                .executeScript("arguments[0].click();", okBtn);
    }

    //Click a visible day cell using JavaScript
    private void clickDayByJs(int dayOfMonth) {
        WebElement cell = getVisibleDayCell(dayOfMonth);
        ((JavascriptExecutor) Driver.getDriver())
                .executeScript("arguments[0].click();", cell);
    }
}
