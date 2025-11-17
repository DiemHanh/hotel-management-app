package User;

import TestBase.TestBaseUser;
import lombok.extern.slf4j.Slf4j;
import models.user.BookingInformation;
import models.user.UserInformation;
import org.testng.annotations.Test;
import page.email.YopMail;
import page.user.*;
import utils.DateUtils;
import utils.TabWindow;

import java.time.LocalDate;

@Slf4j
public class TC01 extends TestBaseUser {
    RoomListPage roomListPage = new RoomListPage();
    RoomDetailPage roomDetailPage = new RoomDetailPage();
    BookingPage bookingPage = new BookingPage();
    PaymentPage paymentPage = new PaymentPage();
    ConfirmPage confirmPage = new ConfirmPage();
    YopMail yopmail = new YopMail();
    TabWindow tabWindow = new TabWindow();

    LocalDate today = DateUtils.getToday();


    @Test
    public void TC01() {
        homePage.openRoomsPage();

        // open random room at detail page
//        roomListPage.openRoomDetailByIndex(faker.number().numberBetween(1, 6));
        roomListPage.openRoomDetailByIndex(4);

        // enter booking info
//        roomDetailPage.inputInformationBooking(today, DateUtils.getFollowingDay(today), faker.number().numberBetween(1, 3), faker.number().numberBetween(1, 3));
        roomDetailPage.submitInformationBooking(new BookingInformation(today, DateUtils.getFollowingDay(today), 1, 0));

        // open yop mail page in a new tab
        String email = yopmail.convertToYopMail(faker.internet().emailAddress());
        yopmail.openYopMailPageInNewTab();
        yopmail.openInboxMail(email.split("@")[0]);

        // back to booking tab
        tabWindow.switchBackToOriginalTab();

        // enter personal info and checkbox
        UserInformation userInfo = new UserInformation(
                faker.name().fullName().toString(),
                email,
                faker.phoneNumber().phoneNumber(),
                faker.address().fullAddress()
        );
        log.info(userInfo.toString());
        bookingPage.submitUserInfo(userInfo);

        // enter credit card and pay now
        paymentPage.submitPaymentBooking(constant.DEFAULT_CREDIT_CARD);

        // confirm page
        sa.assertEquals(confirmPage.getSuccessMessage(), "Thank you! Your booking has been placed. We will contact you to confirm about the booking soon.");

        // switch to email tab
        tabWindow.switchToLastTab();
//        tempMailPage.openLatestEmail();
//        log.info(tempMailPage.getSubjectEmail());

        sa.assertAll();
    }
}
