package TestBase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.Config;

public class TestBaseUser extends TestBase {
    @BeforeMethod
    public void setUpAdmin() {
        setUp(Config.URL_USER_PAGE);
    }

    @AfterMethod
    public void tearDownAdmin() {
        cleanUp();
    }
}
