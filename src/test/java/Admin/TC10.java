package Admin;

import TestBase.TestBaseAdmin;
import page.admin.AdminAddRoomPage;
import page.admin.AdminAllRoomsPage;
import models.admin.Room;
import org.testng.annotations.Test;
import utils.FakerData;


public class TC10 extends TestBaseAdmin {
    AdminAddRoomPage adminAddRoomPage = new AdminAddRoomPage();
    AdminAllRoomsPage adminAllRoomsPage = new AdminAllRoomsPage();
    FakerData fakerData = new FakerData();


    @Test
    public void TC10() {
        //1. Login as Admin
        adminLoginPage.login(constant.DEFAULT_ACCOUNT_ADMIN);

        //2. Expand Rooms label
        adminHomePageAdmin.expandRoomsSection();

        //3. Select Add Room option
        adminHomePageAdmin.clickAddRoom();// rename

        //4. Enter Room info
        adminAddRoomPage.addRoom(fakerData.roomInfo);

        //5. Search for newly created Room
        adminAllRoomsPage.searchRoom(String.valueOf(fakerData.roomInfo.getRoomNumber()));

        // Verify actual match expected result
        sa.assertEquals(adminAllRoomsPage.getSearchResult("Room Number"),
                String.valueOf(fakerData.roomInfo.getRoomNumber()),"Room Number Not Match");

        sa.assertEquals(adminAllRoomsPage.getSearchResult("Room Type"),
                fakerData.roomInfo.getRoomType(), "Room Type Not match");

        sa.assertEquals(adminAllRoomsPage.getSearchResult("Floor"),
                String.valueOf(fakerData.roomInfo.getFloor()),"Floor Not Match");

        sa.assertEquals(adminAllRoomsPage.getStatusAsBoolean(), fakerData.roomInfo.getStatus().booleanValue(),
                "Incorrect Status");

        sa.assertAll();
    }
}
