package userPages;

import org.openqa.selenium.By;
import utils.DateUtils;
import utils.Driver;

import java.time.LocalDate;

public class RoomDetailPage {
    private final By checkInInputLocator = By.id("check-in");
    private final By checkOutInputLocator = By.id("check-out");
    private final By adultInputLocator = By.id("adult");
    private final By childrenInputLocator = By.id("children");
    private final By bookNowBtnLocator = By.cssSelector("[value='Book Now']");

    public void inputInformationBooking(LocalDate checkIndate, LocalDate checkOutdate, int adult, String child) {
        enterCheckInDate(checkIndate);
        enterCheckOutDate(checkOutdate);
        enterNumberAdult(adult);
        enterNumberChild(child);
        clickBookNowBtn();
    }

    public void enterCheckInDate(LocalDate checkInDate) {
        String formattedDate = (checkInDate.format(DateUtils.FORMATTER));
        Driver.getDriver().findElement(checkInInputLocator).sendKeys(formattedDate);
    }

    public void enterCheckOutDate(LocalDate checkOutDate) {
        String formattedDate = checkOutDate.format(DateUtils.FORMATTER);
        Driver.getDriver().findElement(checkOutInputLocator).sendKeys(formattedDate);
    }

    public void enterNumberAdult(int adult) {
        Driver.getDriver().findElement(adultInputLocator).sendKeys(String.valueOf(adult));
    }

    public void enterNumberChild(String child) {
        Driver.getDriver().findElement(childrenInputLocator).sendKeys(child);
    }

    public void clickBookNowBtn() {
        Driver.getDriver().findElement(bookNowBtnLocator).click();
    }
}
