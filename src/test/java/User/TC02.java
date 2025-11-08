package User;

import TestBase.TestBaseUser;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import userPages.*;
import utils.CardInformation;
import utils.DateUtils;

import java.time.LocalDate;

public class TC02 extends TestBaseUser {
    HomePage homePage = new HomePage();
    RoomListPage roomListPage = new RoomListPage();
    RoomDetailPage roomDetailPage = new RoomDetailPage();
    BookingPage bookingPage = new BookingPage();
    PaymentPage paymentPage = new PaymentPage();
    ConfirmPage confirmPage = new ConfirmPage();
    CardInformation cardInfo = new CardInformation();

    LocalDate randomDateNextMonth = DateUtils.parseDate("2025/11/10");

    @Test
    public void TC02() {
        homePage.openRoomsPage();

        // open the second room detail page
        roomListPage.openRoomDetailByIndex(2);

        // enter booking info
        roomDetailPage.inputInformationBooking(randomDateNextMonth, randomDateNextMonth, 1, "0");

        // enter personal info and checkbox
        bookingPage.inputBooking("Phuong Nhi", "cus@gmail.com", "0912345678", "Vietnam");

        // enter credit card and pay now
        paymentPage.enterPaymentBooking(cardInfo.cardNumber, cardInfo.cardName, cardInfo.cardDate, cardInfo.cardCVV); // use object model

        // confirm page
        sa.assertEquals(confirmPage.getSuccessMessage(), "Thank you! Your booking has been placed. We will contact you to confirm about the booking soon.");

        // click rooms menu
        homePage.openRoomsPage();
        roomListPage.openRoomDetailByIndex(2);
        roomDetailPage.inputInformationBooking(randomDateNextMonth, DateUtils.getFollowingDay(randomDateNextMonth), 1, "0");

        sa.assertAll();
    }
}
