package User;

import TestBase.TestBaseUser;
import lombok.extern.slf4j.Slf4j;
import models.user.UserInformation;
import org.testng.annotations.Test;
import page.user.*;
import utils.DateUtils;
import utils.Driver;
import utils.TabWindow;

import java.time.LocalDate;
import java.util.ArrayList;

@Slf4j
public class TC01 extends TestBaseUser {
    RoomListPage roomListPage = new RoomListPage();
    RoomDetailPage roomDetailPage = new RoomDetailPage();
    BookingPage bookingPage = new BookingPage();
    PaymentPage paymentPage = new PaymentPage();
    ConfirmPage confirmPage = new ConfirmPage();
    TempMailPage tempMailPage = new TempMailPage();
    TabWindow tabWindow = new TabWindow();

    LocalDate today = DateUtils.getToday();


    @Test
    public void TC01() {
        homePage.openRoomsPage();

        // open random room at detail page
//        roomListPage.openRoomDetailByIndex(faker.number().numberBetween(1, 6));
        roomListPage.openRoomDetailByIndex(2);

        // enter booking info
//        roomDetailPage.inputInformationBooking(today, DateUtils.getFollowingDay(today), faker.number().numberBetween(1, 3), faker.number().numberBetween(1, 3));
        roomDetailPage.inputInformationBooking(today, DateUtils.getFollowingDay(today), 1, 0);

        // open temp mail page in a new tab to get email
        String email = tempMailPage.getEmail();

        // back to booking tab
        tabWindow.switchBackToOriginalTab();

        // enter personal info and checkbox
        UserInformation userInfo = new UserInformation(
                faker.name().fullName().toString(),
                email,
                faker.phoneNumber().phoneNumber(),
                faker.address().fullAddress()
        );
        bookingPage.inputUserInfo(userInfo); // rename

        // enter credit card and pay now
        paymentPage.enterPaymentBooking(constant.DEFAULT_CREDIT_CARD);

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
