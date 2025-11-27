package Admin;

import TestBase.TestBaseAdmin;
import models.admin.AdminCreditCard;
import models.user.BookingInformation;
import models.user.CreditCard;
import models.user.UserInformation;
import org.testng.annotations.Test;
import page.admin.AdminAddCreditCardPage;
import page.admin.AdminViewAllCreditCardPage;
import page.user.*;
import utils.*;

public class TC02 extends TestBaseAdmin {
    AdminAddCreditCardPage adminAddCreditCardPage = new AdminAddCreditCardPage();
    AdminViewAllCreditCardPage adminViewAllCreditCardPage = new AdminViewAllCreditCardPage();
    HomePage homePage = new HomePage();
    LoginModal loginModal = new LoginModal();
    RoomListPage roomListPage = new RoomListPage();
    RoomDetailPage roomDetailPage = new RoomDetailPage();
    BookingPage bookingPage = new BookingPage();
    Header header = new Header();
    PaymentPage paymentPage = new PaymentPage();

    int searchRow = 1;
    String expectedBalanceMsg = "Balances not enough money !!!";

    AdminCreditCard randomAdminCreditCard = FakerData.generateRandomCreditCard();
    BookingInformation bookingInfo = FakerData.generateBookingInformation();
    UserInformation userInfo = FakerData.generateRandomUser();

    @Test
    public void TC11() {
        //1. Login as Admin
        adminLoginPage.login(Constant.DEFAULT_ACCOUNT_ADMIN);

        //2. Expand Rooms label
        adminHomePage.expandCreditCardSection();

        //3. Select Add CreditCard option
        adminHomePage.navigateToAddCreditCardDetail();

        //4. Add Credit Card
        adminAddCreditCardPage.addCreditCard(randomAdminCreditCard);

        //5. Search for newly created credit card
        adminViewAllCreditCardPage.searchCreditCard(randomAdminCreditCard.getCreditCardNumber());

        //Verify CreditCard Number is displayed as expected > cvv not displayed in Table > set null
        sa.assertEquals(adminViewAllCreditCardPage.getCreditCardByIndex(searchRow).toString(),
                randomAdminCreditCard.toString(), "CreditCard information Not Match");

        //6. Logout
        adminViewAllCreditCardPage.logOut();

        //navigate to User URL
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

        //11. Enter personal info and checkbox
        bookingPage.submitUserInfo(userInfo);

        //12. Enter credit card and pay now
        paymentPage.submitPaymentBooking(new CreditCard(
                randomAdminCreditCard.getCreditCardNumber(),
                randomAdminCreditCard.getOwnerName(),
                DateUtils.formatExpiryDate(randomAdminCreditCard.getExpiryMonth(), randomAdminCreditCard.getExpiryYear()),
                randomAdminCreditCard.getCvvCode()
        ));

        // Verify Error Message: "Balances not enough money !!!" is displayed
        sa.assertEquals(paymentPage.getInsufficientBalanceMessage(), expectedBalanceMsg,
                String.format("Error message not match. expected [%s] but found [%s]",
                        expectedBalanceMsg, paymentPage.getInsufficientBalanceMessage()));

        sa.assertAll();
    }
}
