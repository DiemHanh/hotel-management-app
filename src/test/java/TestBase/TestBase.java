package TestBase;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.asserts.SoftAssert;
import utils.Driver;


public class TestBase {
    protected SoftAssert sa = new SoftAssert();
    protected Faker faker = new Faker();

    public void setUp(String url) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--guest");
        WebDriver driver = new ChromeDriver(options);
        Driver.setDriver(driver);

        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().get(url);
    }

    public void cleanUp() {
        Driver.getDriver().close();
    }
}
