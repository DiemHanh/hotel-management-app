package TestBase;

import adminPages.HomePage;
import adminPages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import utils.Config;

public class TestBaseAdmin extends TestBase {
    public SoftAssert sa = new SoftAssert();
    protected LoginPage loginPage = new LoginPage();
    protected HomePage homePageAdmin = new HomePage();

    @BeforeMethod
    public void setUpAdmin() {
        setUp(Config.URL_ADMIN_PAGE);
    }

//    @AfterMethod
//    public void tearDownAdmin() {
//        cleanUp();
//    }
}
