package User;

import TestBase.TestBaseUser;
import org.testng.annotations.Test;
import utils.Driver;

public class TC07 extends TestBaseUser {
    @Test
    public void TC07() {
        // scroll to bottom
        Driver.scrollToPageBottom();

        // scroll to top
        homePage.scrollToTopPage();

        long scrollPosition = Driver.getCurrentScrollPosition();
        sa.assertEquals(scrollPosition, 0L, "User is not at the top of the page!");
        sa.assertAll();
    }
}
