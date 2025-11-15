package Admin;

import TestBase.TestBaseAdmin;
import page.admin.AddRoomTypePage;
import page.admin.AllRoomTypesPage;
import models.admin.RoomType;
import org.testng.annotations.Test;
import userPages.LoginModal;
import userPages.RoomListPage;
import utils.Config;
import utils.Driver;

public class TC09 extends TestBaseAdmin {
    userPages.HomePage homePage = new userPages.HomePage();
    LoginModal loginModal = new LoginModal();
    RoomListPage roomListPage = new RoomListPage();
    AddRoomTypePage addRoomTypePage = new AddRoomTypePage();
    AllRoomTypesPage allRoomTypesPage = new AllRoomTypesPage();

    String title = faker.address().cityName() + " " +
            faker.options().option("Deluxe Suite", "Family Room", "Presidential Villa", "Ocean View Room") + " " +
            faker.number().numberBetween(1, 9999);
    int price = faker.number().numberBetween(50, 500);
    int adultNumber = faker.number().numberBetween(1, 4);
    int childrenNumber = faker.number().numberBetween(1, 5);
    String description = faker.lorem().sentence(3);

    RoomType roomInfo = new RoomType(title, price, adultNumber, childrenNumber, description);

    @Test
    public void TC09() {

        //1. Login as Admin
        loginPage.login(constant.DEFAULT_ACCOUNT_ADMIN);

        //2. Expand Room Types label
        homePageAdmin.expandRoomTypesSection();

        //3. Select Add Room Type option
        homePageAdmin.clickAddRoomType();

        //4. Enter Room Type information
        addRoomTypePage.addRoomType(roomInfo);

        //6. Search for newly created Room type
        allRoomTypesPage.searchRoomType(roomInfo.getTitle());

        // Verify actual match expected result
        sa.assertEquals(allRoomTypesPage.getSearchResult(), roomInfo.getTitle(), "Room type title in search result does not match expected value");

        //7. Logout
        allRoomTypesPage.clickLogOutButton(); // rename: logout()

        //navigate to User URL
        Driver.getDriver().navigate().to(Config.URL_USER_PAGE);

        //open login modal
        homePage.openLoginModal();

        //8. Login as User
        loginModal.login(constant.DEFAULT_ACCOUNT_USER);

        //9. Open Rooms Page
        homePage.openRoomsPage();

        //10. Click on "View detail" button of newly created Room
        roomListPage.clickViewDetailsByRoomName(roomInfo.getTitle()); // rename to openRoomDetailByName
// assert equal same room or not
        // verify name and price
        sa.assertAll();
    }
}
