package page.admin;

import io.qameta.allure.Step;
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

    @Step("Add promotion")
    public void addPromotion(Promotion promotion, String type){
        enterPromotionName(promotion.getPromotionName());
        enterPromotionCode(promotion.getPromotionCode());
        selectStartDateToday();
        selectEndDateTomorrow();
        openPromotionTypeDropDown(type);
        enterPromotionValue(promotion.getPromotionValue());
        clickSubmitButton();
    }

    @Step("Enter promotion name: {0}")
    public void enterPromotionName(String promotionName) {
        Driver.getDriver().findElement(promotionNameLocator).sendKeys(promotionName);
    }

    @Step("Enter promotion code: {0}")
    public void enterPromotionCode(String promotionCode) {
        Driver.getDriver().findElement(promotionCodeLocator).sendKeys(promotionCode);
    }

    @Step("Enter promotion value: {0}")
    public void enterPromotionValue(int promotionValue) {
        Driver.getDriver().findElement(promotionValueLocator).clear();
        Driver.getDriver().findElement(promotionValueLocator).sendKeys(String.valueOf(promotionValue));
    }

    @Step("Select promotion type: {0}")
    public void openPromotionTypeDropDown(String promotion){
        Driver.getDriver().findElement(promotionTypeLocator).click();
        selectPromotionTypeFromDropdown(promotion);
    }

    @Step("Choose promotion type from list: {0}")
    public void selectPromotionTypeFromDropdown(String promotionType){
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

    @Step("Select start date: Today")
    public void selectStartDateToday() {
        Driver.getDriver().findElement(periodStartDateLocator).click();
        clickVisibleOkByJs();
    }

    @Step("Select end date: Tomorrow")
    public void selectEndDateTomorrow() {
        Driver.getDriver().findElement(periodEndDateLocator).click();
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        if (tomorrow.getMonth().equals(today.getMonth())) {
            clickDayByJs(tomorrow.getDayOfMonth());
        } else {
            Driver.getWebDriverWait()
                    .until(ExpectedConditions.elementToBeClickable(nextButtonLocator))
                    .click();
            clickDayByJs(1);
        }
        clickVisibleOkByJs();
    }

    @Step("Submit promotion")
    public void clickSubmitButton() {
        Driver.getDriver().findElement(submitBtnLocator).click();
    }

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

    private void clickVisibleOkByJs() {
        WebElement okBtn = getVisibleOkButton();
        ((JavascriptExecutor) Driver.getDriver())
                .executeScript("arguments[0].click();", okBtn);
    }

    private void clickDayByJs(int dayOfMonth) {
        WebElement cell = getVisibleDayCell(dayOfMonth);
        ((JavascriptExecutor) Driver.getDriver())
                .executeScript("arguments[0].click();", cell);
    }
}
