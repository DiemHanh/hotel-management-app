package User;

import TestBase.TestBaseUser;
import models.user.BookingInformation;
import models.user.UserInformation;
import org.testng.annotations.Test;
import page.user.*;
import utils.Constant;
import utils.DateUtils;
import utils.FakerData;

import java.time.LocalDate;

public class TC03 extends TestBaseUser {
    RoomListPage roomListPage = new RoomListPage();
    RoomDetailPage roomDetailPage = new RoomDetailPage();
    LoginModal loginModal = new LoginModal();
    BookingPage bookingPage = new BookingPage();
    PaymentPage paymentPage = new PaymentPage();

    LocalDate today = DateUtils.getToday();
    UserInformation userInfo = FakerData.generateRandomUser();

    @Test
    public void TC03() {
        homePage.openLoginModal();
        loginModal.login(Constant.DEFAULT_ACCOUNT_USER);

        homePage.openRoomsPage();
        // open random room at detail page
        roomListPage.openRoomDetailByIndex(faker.number().numberBetween(1, 6));

        // enter booking info
//        roomDetailPage.inputInformationBooking(today, DateUtils.getFollowingDay(today), faker.number().numberBetween(1, 3), faker.number().numberBetween(1, 3));
        roomDetailPage.submitInformationBooking(new BookingInformation(today, DateUtils.getFollowingDay(today), 1, 0));

        // enter personal info and checkbox
        bookingPage.submitUserInfo(userInfo);

        // enter credit card and pay now
        paymentPage.submitPaymentBooking(Constant.DEFAULT_CREDIT_CARD);

        // From Home page, click to Account & Settings in Menu bar
    }
}
