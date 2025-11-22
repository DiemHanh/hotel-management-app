package Admin;

import TestBase.TestBaseAdmin;
import page.admin.AdminAddRoomTypePage;
import page.admin.AdminAllRoomTypesPageAdmin;
import models.admin.RoomType;
import org.testng.annotations.Test;
import page.user.HomePage;
import page.user.LoginModal;
import page.user.RoomDetailPage;
import page.user.RoomListPage;
import utils.*;

public class TC09 extends TestBaseAdmin {
    HomePage homePage = new HomePage();
    LoginModal loginModal = new LoginModal();
    RoomListPage roomListPage = new RoomListPage();
    AdminAddRoomTypePage adminAddRoomTypePage = new AdminAddRoomTypePage();
    AdminAllRoomTypesPageAdmin adminAllRoomTypesPage = new AdminAllRoomTypesPageAdmin();
    RoomDetailPage roomDetailPage = new RoomDetailPage();
    int searchRow = 1;

    RoomType randomRoomType = FakerData.generateRandomRoomType();

    @Test
    public void TC09() {

        //1. Login as Admin
        adminLoginPage.login(Constant.DEFAULT_ACCOUNT_ADMIN);

        //2. Expand Room Types label
        adminHomePage.expandRoomTypesSection();

        //3. Select Add Room Type option
        adminHomePage.navigateToAddRoomTypeDetail();

        //4. Enter Room Type information
        adminAddRoomTypePage.addRoomType(randomRoomType);

        //6. Search for newly created Room type
        adminAllRoomTypesPage.searchRoomType(randomRoomType.getTitle());
        adminAllRoomTypesPage.getPriceByRowIndex(1);

        // Verify actual match expected result > no description in RoomTypeTable > set Description = ""
        randomRoomType.setDescription("");
        sa.assertEquals(adminAllRoomTypesPage.getRoomTypeByIndex(searchRow).toString(),
                randomRoomType.toString(), "Room Type in search result does not match expected value");

        //7. Logout
        adminAllRoomTypesPage.logOut();

        //navigate to User URL
        Driver.getDriver().navigate().to(Config.URL_USER_PAGE);

        //open login modal
        homePage.openLoginModal();

        //8. Login as User
        loginModal.login(Constant.DEFAULT_ACCOUNT_USER);

        //9. Open Rooms Page
        homePage.openRoomsPage();

        //10. Click on "View detail" button of newly created Room
        roomListPage.openRoomDetailByName(randomRoomType.getTitle());

        // Verify RoomType name
        sa.assertEquals(roomDetailPage.getRoomName(), randomRoomType.getTitle(), "Room Name not match expected value");

        // Verify RoomType price
        sa.assertEquals(roomDetailPage.getRoomPrice(), (float) randomRoomType.getPrice(), "Price not match expected value");

        sa.assertAll();
    }
}
