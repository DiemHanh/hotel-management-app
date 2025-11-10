package TestBase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import userPages.HomePage;
import utils.Config;

public class TestBaseUser extends TestBase {
    protected HomePage homePage = new HomePage();

    @BeforeMethod
    public void setUpUser() {
        setUp(Config.URL_USER_PAGE);
    }

//    @AfterMethod
//    public void tearDownUser() {
//        cleanUp();
//    }
}
