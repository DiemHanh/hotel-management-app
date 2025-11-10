package Admin;

import TestBase.TestBaseAdmin;
import adminPages.AddRoomPage;
import adminPages.AllRoomsPage;
import models.admin.Room;
import org.testng.annotations.Test;
import utils.AdminInformation;

public class TC10 extends TestBaseAdmin {
    AdminInformation adminInfo = new AdminInformation();
    AddRoomPage addRoomPage = new AddRoomPage();
    AllRoomsPage allRoomsPage = new AllRoomsPage();

    int roomNumber = faker.number().numberBetween(1, 500);
    int floor = faker.number().numberBetween(1, 10);
    String description = faker.lorem().sentence(10);

    Room roomInfo = new Room(roomNumber, null, floor, description, null);

    @Test
    public void TC10() {
        //1. Login as Admin
        loginPage.login(adminInfo.username, adminInfo.password);

        //2. Expand Rooms label
        homePageAdmin.expandRoomsSection();

        //3. Select Add Room option
        homePageAdmin.clickAddRoom();// rename

        //4. Enter Room info
        addRoomPage.addRoom(roomInfo);

        //5. Click Submit button
//        addRoomPage.clickSubmitButton();

        //6. Search for newly created Room
        allRoomsPage.searchRoom(String.valueOf(roomInfo.getRoomNumber()));

        // Verify actual match expected result
        sa.assertEquals(allRoomsPage.getSearchResult(), String.valueOf(roomInfo.getRoomNumber()), "Room Number in search result does not match expected value");
// verify all column
        sa.assertAll();
    }
}
