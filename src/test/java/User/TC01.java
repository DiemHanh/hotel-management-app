package User;

import TestBase.TestBaseUser;
import lombok.extern.slf4j.Slf4j;
import models.user.BookingInformation;
import models.user.UserInformation;
import org.testng.annotations.Test;
import page.email.YopMail;
import page.user.*;
import utils.Constant;
import utils.DateUtils;
import utils.FakerData;

import java.time.LocalDate;

@Slf4j
public class TC01 extends TestBaseUser {
    RoomListPage roomListPage = new RoomListPage();
    RoomDetailPage roomDetailPage = new RoomDetailPage();
    BookingPage bookingPage = new BookingPage();
    PaymentPage paymentPage = new PaymentPage();
    ConfirmPage confirmPage = new ConfirmPage();
    YopMail yopmail = new YopMail();

    UserInformation userInfo = FakerData.generateRandomUser();
    BookingInformation bookingInfo = FakerData.generateBookingInformation();

    @Test
    public void TC01() {
        homePage.openRoomsPage();

        // open random room at detail page
        roomListPage.openRoomDetailByIndex(faker.number().numberBetween(1, 6));

        // enter booking info
        roomDetailPage.submitBookingInformation(bookingInfo);

        // enter personal info and checkbox
        bookingPage.submitUserInfo(userInfo);

        // enter credit card and pay now
        paymentPage.submitPaymentBooking(Constant.DEFAULT_CREDIT_CARD);

        // confirm page
        sa.assertEquals(confirmPage.getSuccessMessage(), "Thank you! Your booking has been placed. We will contact you to confirm about the booking soon.");

        // open yop mail page in a new tab
        yopmail.openYopMailPageInNewTab();
        yopmail.openInboxMail(userInfo.getEmail());
        yopmail.openInbox();

        sa.assertEquals(yopmail.getSubjectEmail(), "Your booking has been placed !");
        sa.assertAll();
    }
}
