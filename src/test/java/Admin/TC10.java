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
        adminHomePage.expandRoomsSection();

        //3. Select Add Room option
        adminHomePage.navigateToAddRoomDetail();

        //4. Enter Room info
        adminAddRoomPage.addRoom(randomRoom);

        //5. Search for newly created Room
        adminAllRoomsPage.searchRoom(String.valueOf(randomRoom.getRoomNumber()));

        //6. Verify actual match expected result > no description in RoomTable > set Description = ""
        sa.assertEquals(adminAllRoomsPage.getRoomByIndex(searchRow).toString(),
                randomRoom.toString(), "Room in search result does not match expected value");

        sa.assertAll();
    }
}
