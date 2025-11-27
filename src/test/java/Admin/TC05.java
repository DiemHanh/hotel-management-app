package Admin;

import TestBase.TestBaseAdmin;
import models.admin.Promotion;
import models.user.BookingInformation;
import org.testng.annotations.Test;
import page.admin.AdminAddPromotionPage;
import page.admin.AdminViewAllPromotionsPage;
import page.user.*;
import utils.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TC05 extends TestBaseAdmin {
    AdminAddPromotionPage adminAddPromotionPage = new AdminAddPromotionPage();
    AdminViewAllPromotionsPage adminViewAllPromotionsPage = new AdminViewAllPromotionsPage();
    HomePage homePage = new HomePage();
    LoginModal loginModal = new LoginModal();
    RoomListPage roomListPage = new RoomListPage();
    RoomDetailPage roomDetailPage = new RoomDetailPage();
    Header header = new Header();
    BookingPage bookingPage = new BookingPage();

    int searchRow = 1;
    String promotionType = "FIXED";
    String dateFormat = "yyyy-MM-dd";

    Promotion randomPromotion = FakerData.generateRandomPromotion();
    BookingInformation bookingInfo = FakerData.generateBookingInformation();

    @Test
    public void TC05() {
        //1. Login as Admin
        adminLoginPage.login(Constant.DEFAULT_ACCOUNT_ADMIN);

        //2. Expand Promotion label
        adminHomePage.expandPromotionSection();

        //3. Navigate to Add Promotion Detail
        adminHomePage.navigateToAddPromotionDetail();

        //4. Add Promotion
        adminAddPromotionPage.addPromotion(randomPromotion, promotionType);
        randomPromotion.setPeriodStartDate(DateUtils.getToday().format(DateTimeFormatter.ofPattern(dateFormat)));
        randomPromotion.setPeriodEndDate(DateUtils.getFollowingDay(LocalDate.now()).format(DateTimeFormatter.ofPattern(dateFormat)));
        randomPromotion.setPromotionType(promotionType);

        //5. Search latest promotion code in list view
        adminViewAllPromotionsPage.searchPromotion(randomPromotion.getPromotionName());

        // Verify promotion code is created correctly
        sa.assertEquals(adminViewAllPromotionsPage.getPromotionByIndex(searchRow).toString(),
                randomPromotion.toString(), "Promotion information Not Match");

        //6. Logout
        adminViewAllPromotionsPage.logOut();

        // Navigate to User URL
        Driver.getDriver().navigate().to(Config.URL_USER_PAGE);

        //open login modal
        header.openLoginModal();

        //7. Login as User
        loginModal.login(Constant.DEFAULT_ACCOUNT_USER);

        //8. Open Rooms Page
        homePage.openRoomsPage();

        //9. Open random Room by index
        roomListPage.openRoomDetailByIndex(faker.number().numberBetween(1, 6));

        //10. From Room Details page, enter Booking information then click on Book Now button
        roomDetailPage.submitBookingInformation(bookingInfo);

        //11.Apply promotion code successfully
        bookingPage.applyPromoCode(randomPromotion.getPromotionCode());

        // Verify promo code is applied and calculated correctly
        sa.assertEquals(bookingPage.calculateGrandTotal(), bookingPage.getGrandTotalAmount(),
                "Grand Total is incorrect!");

        sa.assertAll();
    }
}
