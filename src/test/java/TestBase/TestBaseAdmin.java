package TestBase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.admin.AdminHomePage;
import page.admin.AdminLoginPage;
import utils.Config;

public class TestBaseAdmin extends TestBase {
    protected AdminLoginPage adminLoginPage = new AdminLoginPage();
    protected AdminHomePage adminHomePage = new AdminHomePage();

    @BeforeMethod
    public void setUpAdmin() {
        setUp(Config.URL_ADMIN_PAGE);
    }

    @AfterMethod
    public void tearDownAdmin() {
        cleanUp();
    }
}
