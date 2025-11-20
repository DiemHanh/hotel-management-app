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
    int searchRow = 1;
    
    Room randomRoom = FakerData.generateRandomRoom();
    
    @Test
    public void TC10() {
        //1. Login as Admin
        adminLoginPage.login(Constant.DEFAULT_ACCOUNT_ADMIN);

        //2. Expand Rooms label
        adminHomePageAdmin.expandRoomsSection();

        //3. Select Add Room option
        adminHomePageAdmin.navigateToAddRoomDetail();

        //4. Enter Room info
        adminAddRoomPage.addRoom(randomRoom);

        //5. Search for newly created Room
        adminAllRoomsPage.searchRoom(String.valueOf(randomRoom.getRoomNumber()));

        // Verify actual match expected result
        sa.assertEquals(adminAllRoomsPage.getSearchResult("Room Number", searchRow),
                String.valueOf(randomRoom.getRoomNumber()), "Room Number Not Match");

        sa.assertEquals(adminAllRoomsPage.getSearchResult("Room Type", searchRow),
                randomRoom.getRoomType(), "Room Type Not match");

        sa.assertEquals(adminAllRoomsPage.getSearchResult("Floor", searchRow),
                String.valueOf(randomRoom.getFloor()), "Floor Not Match");

        sa.assertEquals(adminAllRoomsPage.getStatusAsBoolean(searchRow), randomRoom.getStatus().booleanValue(),
                "Incorrect Status");

        sa.assertAll();
    }
}
