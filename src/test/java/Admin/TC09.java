package Admin;

import TestBase.TestBaseAdmin;
import adminPages.AddRoomTypePage;
import adminPages.HomePage;
import adminPages.LoginPage;
import models.admin.RoomType;
import org.testng.annotations.Test;

public class TC09 extends TestBaseAdmin {
    HomePage homePageAdmin = new HomePage();
    AddRoomTypePage addRoomTypePage = new AddRoomTypePage();


    @Test
    public void TC09() throws InterruptedException {
        //1. Login as Admin
        loginPage.login("admin","123456");
        //2. Expand Room Types label
        homePageAdmin.expandRoomTypesSection();
        //3. Select Add Room Type option
        homePageAdmin.clickAddRoomType();
        //4. Enter Room Type infor

        addRoomTypePage.openAdultCapacityMenu();
        addRoomTypePage.selectAdultCapacity(1);
    }



}
