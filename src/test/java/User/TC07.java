package User;

import TestBase.TestBaseUser;
import org.testng.annotations.Test;
import utils.TabWindow;

public class TC07 extends TestBaseUser {
    TabWindow tabWindow = new TabWindow();

    @Test
    public void TC07() {
        // scroll to bottom
        tabWindow.scrollToPageBottom();

        // scroll to top
        homePage.scrollToTopPage();

        long scrollPosition = tabWindow.getCurrentScrollPosition();
        sa.assertEquals(scrollPosition, 0L, "User is not at the top of the page!");
        sa.assertAll();
    }
}
