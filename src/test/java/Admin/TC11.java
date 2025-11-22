package Admin;

import TestBase.TestBaseAdmin;
import models.admin.CreditCard;
import org.testng.annotations.Test;
import page.admin.AdminAddCreditCardPage;
import page.admin.AdminViewAllCreditCardPage;
import page.user.HomePage;
import page.user.LoginModal;
import page.user.RoomDetailPage;
import page.user.RoomListPage;
import utils.*;

import java.math.BigDecimal;

public class TC11 extends TestBaseAdmin {
    AdminAddCreditCardPage adminAddCreditCardPage = new AdminAddCreditCardPage();
    AdminViewAllCreditCardPage adminViewAllCreditCardPage = new AdminViewAllCreditCardPage();
    HomePage homePage = new HomePage();
    LoginModal loginModal = new LoginModal();
    RoomListPage roomListPage = new RoomListPage();
    RoomDetailPage roomDetailPage = new RoomDetailPage();

    int searchRow = 1;

    CreditCard randomCreditCard = FakerData.generateRandomCreditCard();

    //add before method > pre-condition
    @Test
    public void TC11() {
        //1. Login as Admin
        adminLoginPage.login(Constant.DEFAULT_ACCOUNT_ADMIN);

        //2. Expand Rooms label
        adminHomePage.expandCreditCardSection();

        //3. Select Add CreditCard option
        adminHomePage.navigateToAddCreditCardDetail();

        //4. Add Credit Card
        adminAddCreditCardPage.addCreditCard(randomCreditCard);

        //5. Search for newly created credit card
        adminViewAllCreditCardPage.searchCreditCard(randomCreditCard.getCreditCardNumber());

        // Verify CreditCard Number is displayed as expected > cvv not displayed in Table > set null
        randomCreditCard.setCvvCode("");
        sa.assertEquals(adminViewAllCreditCardPage.getCreditCardByIndex(searchRow).toString()
                        , randomCreditCard.toString(), "CreditCard information Not Match");

        //6. Logout & navigate to User URL
        adminViewAllCreditCardPage.logOut();
        Driver.getDriver().navigate().to(Config.URL_USER_PAGE);

        //open login modal
        homePage.openLoginModal();

        //. Login as User
        loginModal.login(Constant.DEFAULT_ACCOUNT_USER);

        //. Open Rooms Page
        homePage.openRoomsPage();

        // Open random Room by index
        roomListPage.openRoomDetailByIndex(faker.number().numberBetween(1, 6));

        //. From Room Details page, enter Booking information then click on Book Now button
//        roomDetailPage.submitInformationBooking();

        sa.assertAll();
    }


}
