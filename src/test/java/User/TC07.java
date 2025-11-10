package User;

import TestBase.TestBaseUser;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import utils.Driver;

public class TC07 extends TestBaseUser {

    @Test
    public void TC07() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver(); // move it to Driver utils

        // scroll to bottom
        js.executeScript("window.scrollTo({top: document.documentElement.scrollHeight, behavior: 'smooth'});");

        homePage.scrollToTopPage();

//        Number scrollPosition = (Number) js.executeScript("return window.pageYOffset;");
        long scrollPosition = (long) js.executeScript("return window.pageYOffset;"); // move too
        sa.assertEquals(scrollPosition, 0L, "User is not at the top of the page!");
        sa.assertAll();
    }
}
