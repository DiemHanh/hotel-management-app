package page.user;

import models.user.BookingInformation;
import models.user.Room;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.DateUtils;
import utils.Driver;

import java.time.LocalDate;

public class RoomDetailPage {
    private final By checkInInputLocator = By.id("check-in");
    private final By checkOutInputLocator = By.id("check-out");
    private final By adultInputLocator = By.id("adult");
    private final By childrenInputLocator = By.id("children");
    private final By bookNowBtnLocator = By.cssSelector("[value='Book Now']");
    private final By roomNameLocator = By.cssSelector("h3.float-left");
    private final By roomPriceLocator = By.cssSelector(".yemm_top_price > strong");

    public void submitInformationBooking(BookingInformation bookingInformation) {
        enterCheckInDate(bookingInformation.getCheckIndate());
        enterCheckOutDate(bookingInformation.getCheckOutdate());
        enterNumberAdult(bookingInformation.getAdult());
        enterNumberChild(bookingInformation.getChild());
        clickBookNowBtn();
    }

    public void enterCheckInDate(LocalDate checkInDate) {
        String formattedDate = checkInDate.format(DateUtils.FORMATTER);
        Driver.getDriver().findElement(checkInInputLocator).sendKeys(formattedDate);
    }

    public void enterCheckOutDate(LocalDate checkOutDate) {
        String formattedDate = checkOutDate.format(DateUtils.FORMATTER);
        Driver.getDriver().findElement(checkOutInputLocator).sendKeys(formattedDate);
    }

    public void enterNumberAdult(int adult) {
        WebElement adultInput = Driver.getDriver().findElement(adultInputLocator);
        adultInput.clear();
        adultInput.sendKeys(String.valueOf(adult));
    }

    public void enterNumberChild(int child) {
        WebElement childInput = Driver.getDriver().findElement(childrenInputLocator);
        childInput.clear();
        childInput.sendKeys(String.valueOf(child));
    }

    public void clickBookNowBtn() {
        Driver.getDriver().findElement(bookNowBtnLocator).click();
    }

    public String getRoomName() {
        return Driver.getDriver().findElement(roomNameLocator).getText();
    }

    public Float getRoomPrice() {
        return Float.parseFloat(Driver.getDriver().findElement(roomPriceLocator).getText().substring(1));
    }

    public Room getRoomDetail() {
        return new Room(getRoomName(), getRoomPrice());
    }
}
