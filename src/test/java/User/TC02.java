package User;

import TestBase.TestBaseUser;
import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import userPages.*;
import utils.DateUtils;

import java.time.LocalDate;
import java.util.Random;

public class TC02 extends TestBaseUser {
    HomePage homePage = new HomePage();
    RoomListPage roomListPage = new RoomListPage();
    RoomDetailPage roomDetailPage = new RoomDetailPage();
    BookingPage bookingPage = new BookingPage();
    CheckoutPage checkoutPage = new CheckoutPage();
    ConfirmPage confirmPage = new ConfirmPage();

    LocalDate randomDateNextMonth = DateUtils.parseDate("2025/11/10");
    Faker numberAdult = new Faker(new Random(24));
    SoftAssert sa = new SoftAssert();

    @Test
    public void TC02() {
        homePage.openRoomsPage();
        System.out.println("num " + numberAdult);
        // verify rooms page is displayed
        sa.assertEquals(roomListPage.getTitleRoomsPage(), "Rooms");

        // open the second room detail page
        roomListPage.openRoomDetailByIndex(2);

        // verify room detail page is displayed
        sa.assertEquals(roomDetailPage.getTitleRoomsPage(), "Room Details");

        // enter booking info
        roomDetailPage.inputInformationBooking(randomDateNextMonth, randomDateNextMonth, 1, "0");

        // enter personal info and checkbox
        bookingPage.inputBooking("Phuong Nhi", "cus@gmail.com", "0912345678", "Vietnam");

        // enter credit card and pay now
        checkoutPage.enterCheckoutBooking("2222 3333 4444 5555", "JOHN HENRY", "12/25", "123"); // enterPaymentBooking, use object model, define constant for the info

        // confirm page
        sa.assertEquals(confirmPage.getSuccessMessage(), "Thank you! Your booking has been placed. We will contact you to confirm about the booking soon.");

        // click rooms menu
        homePage.openRoomsPage();
        roomListPage.openRoomDetailByIndex(2);
        roomDetailPage.inputInformationBooking(randomDateNextMonth, DateUtils.getFollowingDay(randomDateNextMonth), 1, "0");

        sa.assertAll();
    }
}
