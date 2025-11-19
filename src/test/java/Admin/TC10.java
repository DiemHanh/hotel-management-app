package Admin;

import TestBase.TestBaseAdmin;
import models.admin.Room;
import org.testng.annotations.Test;
import page.admin.AdminAddRoomPage;
import page.admin.AdminAllRoomsPage;
import utils.Constant;
import utils.FakerData;


public class TC10 extends TestBaseAdmin {
    AdminAddRoomPage adminAddRoomPage = new AdminAddRoomPage();
    AdminAllRoomsPage adminAllRoomsPage = new AdminAllRoomsPage();

    Room randomRoom = FakerData.generateRandomRoom();

    @Test
    public void TC10() {
        //1. Login as Admin
        adminLoginPage.login(Constant.DEFAULT_ACCOUNT_ADMIN);

        //2. Expand Rooms label
        adminHomePageAdmin.expandRoomsSection();

        //3. Select Add Room option
        adminHomePageAdmin.clickAddRoom(); // rename

        //4. Enter Room info
        adminAddRoomPage.addRoom(randomRoom);

        //5. Search for newly created Room
        adminAllRoomsPage.searchRoom(String.valueOf(randomRoom.getRoomNumber()));

        // Verify actual match expected result
        sa.assertEquals(adminAllRoomsPage.getSearchResult("Room Number", 1),
                String.valueOf(randomRoom.getRoomNumber()), "Room Number Not Match");

        sa.assertEquals(adminAllRoomsPage.getSearchResult("Room Type", 1),
                randomRoom.getRoomType(), "Room Type Not match");

        sa.assertEquals(adminAllRoomsPage.getSearchResult("Floor", 1),
                String.valueOf(randomRoom.getFloor()), "Floor Not Match");

        sa.assertEquals(adminAllRoomsPage.getStatusAsBoolean(1), randomRoom.getStatus().booleanValue(),
                "Incorrect Status");

        sa.assertAll();
    }
}
