package User;

import TestBase.TestBaseUser;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import models.user.Payment;
import models.user.UserInformation;
import org.testng.annotations.Test;
import userPages.*;
import utils.CardInformation;
import utils.DateUtils;
import utils.Driver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

@Slf4j
public class TC01 extends TestBaseUser {
    HomePage homePage = new HomePage();
    RoomListPage roomListPage = new RoomListPage();
    RoomDetailPage roomDetailPage = new RoomDetailPage();
    BookingPage bookingPage = new BookingPage();
    PaymentPage paymentPage = new PaymentPage();
    ConfirmPage confirmPage = new ConfirmPage();
    CardInformation cardInfo = new CardInformation();
    TempMailPage tempMailPage = new TempMailPage();
    BasePage basePage = new BasePage();
    Faker faker = new Faker((new Locale("vi")));

    LocalDate today = DateUtils.getToday();

    Payment payment = new Payment(cardInfo.cardNumber, cardInfo.cardName, cardInfo.cardDate, cardInfo.cardCVV);

    @Test
    public void TC01() {
        homePage.openRoomsPage();

        // open random room at detail page
        roomListPage.openRoomDetailByIndex(faker.number().numberBetween(1, 6));
//        roomListPage.openRoomDetailByIndex(3);

        // enter booking info
        roomDetailPage.inputInformationBooking(today, DateUtils.getFollowingDay(today), faker.number().numberBetween(1, 3), faker.number().numberBetween(1, 3));
//        roomDetailPage.inputInformationBooking(today, DateUtils.getFollowingDay(today), 1, 0);

        // open temp mail page in a new tab to get email
        String email = tempMailPage.getEmail();

        // back to booking tab
        basePage.switchBackToOriginalTab();

        // enter personal info and checkbox
        UserInformation userInfo = new UserInformation(
                faker.name().fullName().toString(),
                email,
                faker.phoneNumber().phoneNumber(),
                faker.address().fullAddress()
        );
        bookingPage.inputUserInfo(userInfo);

        // enter credit card and pay now
        paymentPage.enterPaymentBooking(payment);

        // confirm page
        sa.assertEquals(confirmPage.getSuccessMessage(), "Thank you! Your booking has been placed. We will contact you to confirm about the booking soon.");

        // switch to email tab
        ArrayList<String> tabs = new ArrayList<>(Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(tabs.get(tabs.size() - 1));
        tempMailPage.openLatestEmail();
        log.info(tempMailPage.getSubjectEmail());

        sa.assertAll();
    }
}
