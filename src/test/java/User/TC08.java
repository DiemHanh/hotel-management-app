package User;

import TestBase.TestBaseUser;
import models.user.BookingInformation;
import models.user.UserInformation;
import org.testng.annotations.Test;
import page.user.*;
import utils.Constant;
import utils.FakerData;

public class TC08 extends TestBaseUser {
    RoomListPage roomListPage = new RoomListPage();
    RoomDetailPage roomDetailPage = new RoomDetailPage();
    BookingPage bookingPage = new BookingPage();
    PaymentPage paymentPage = new PaymentPage();
    ConfirmPage confirmPage = new ConfirmPage();
    SearchPage searchPage = new SearchPage();
    Header header = new Header();

    UserInformation userInfo = FakerData.generateRandomUser();
    BookingInformation bookingInfo = FakerData.generateBookingInformation();

    private String bookingId;

    @Test
    public void TC08() {
        homePage.openRoomsPage();

        // open random room at detail page
        roomListPage.openRoomDetailByIndex(faker.number().numberBetween(1, 6));

        // enter booking info
        roomDetailPage.submitBookingInformation(bookingInfo);

        // enter personal info and checkbox
        bookingPage.submitUserInfo(userInfo);

        // enter credit card and pay now
        paymentPage.submitPaymentBooking(Constant.DEFAULT_CREDIT_CARD);

        // get bookingId to search after booking successfully
        bookingId = confirmPage.getBookingId();
        header.searchBookingNumber(bookingId);

        // verify that can search for a room booked
        sa.assertEquals(bookingId, searchPage.getBookingId());
        sa.assertAll();
    }
}
