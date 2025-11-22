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
import utils.TabWindow;

import java.time.LocalDate;

@Slf4j
public class TC03 extends TestBaseUser {
    RoomListPage roomListPage = new RoomListPage();
    RoomDetailPage roomDetailPage = new RoomDetailPage();
    LoginModal loginModal = new LoginModal();
    BookingPage bookingPage = new BookingPage();
    PaymentPage paymentPage = new PaymentPage();
    ConfirmPage confirmPage = new ConfirmPage();
    Header header = new Header();
    MyBookingPage myBookingPage = new MyBookingPage();

    LocalDate today = DateUtils.getToday();
    UserInformation userInfo = FakerData.generateRandomUser();
    BookingInformation bookingInfo = FakerData.generateBookingInformation();

    private String bookingId;
    private int bookingIndex;

    @Test
    public void TC03() {
        header.openLoginModal();
        loginModal.login(Constant.DEFAULT_ACCOUNT_USER);

        homePage.openRoomsPage();
        // open random room at detail page
//        roomListPage.openRoomDetailByIndex(faker.number().numberBetween(1, 6));
        roomListPage.openRoomDetailByIndex(6);

        // enter booking info
        roomDetailPage.submitBookingInformation(bookingInfo);

        // enter personal info and checkbox
        bookingPage.submitUserInfo(userInfo);

        // enter credit card and pay now
        paymentPage.submitPaymentBooking(Constant.DEFAULT_CREDIT_CARD);

        // get bookingId
        bookingId = confirmPage.getBookingId();

        // navigate to my booking page
        header.navigateToMyBookingPage();
        bookingIndex = myBookingPage.getIndexFromBookingNumber(bookingId);

        // open cancel newly booking modal
        myBookingPage.openCancelModalByIndex(bookingIndex);

        // cancel newly booking
        myBookingPage.cancelBooking();

        // navigate cancel bookings page
        header.navigateToCancelBookingPage();

        // verify the cancellation booking ID exists in cancelled bookings

    }
}
