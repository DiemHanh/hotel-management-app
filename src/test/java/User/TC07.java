package User;

import TestBase.TestBaseUser;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import userPages.HomePage;
import utils.Driver;

public class TC07 extends TestBaseUser {
    HomePage homePage = new HomePage();

    @Test
    public void TC07() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

        // scroll to bottom
        js.executeScript("window.scrollTo({top: document.documentElement.scrollHeight, behavior: 'smooth'});");

        homePage.scrollToTopPage();

        Number scrollPosition = (Number) js.executeScript("return window.pageYOffset;");
        sa.assertEquals(scrollPosition.longValue(), 0L, "User is not at the top of the page!");
        sa.assertAll();
    }
}
