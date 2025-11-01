import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.Config;
import utils.Driver;

public class TestBase {
    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--guest");
        WebDriver driver = new ChromeDriver(options);
        Driver.setDriver(driver);

        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().get(Config.BASE_URL);
    }

    @AfterMethod
    public void cleanUp() {
        Driver.getDriver().close();
    }
}
