package TestBase;

import page.admin.AdminHomePage;
import page.admin.AdminLoginPage;
import org.testng.annotations.BeforeMethod;
import utils.Config;

public class TestBaseAdmin extends TestBase {
    protected AdminLoginPage adminLoginPage = new AdminLoginPage();
    protected AdminHomePage adminHomePageAdmin = new AdminHomePage();

    @BeforeMethod
    public void setUpAdmin() {
        setUp(Config.URL_ADMIN_PAGE);
    }

//    @AfterMethod
//    public void tearDownAdmin() {
//        cleanUp();
//    }
}
