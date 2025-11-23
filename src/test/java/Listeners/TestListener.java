package Listeners;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.Driver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class TestListener implements ITestListener {
    @Override
    public void onTestSuccess(ITestResult result) {
        captureScreenshot(result.getName());
        log.info("Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        captureScreenshot(result.getName());
        log.info("Test failed: " + result.getName());
    }

    private void captureScreenshot(String testName) {
        try {
            WebDriver driver = Driver.getDriver();

            // Take screenshot
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            // Create screenshots folder if not exists
            File screenshotDir = new File("screenshots");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            // Generate filename
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String fileName = testName + "_" + timestamp + ".png";
            File destination = new File("screenshots/" + fileName);

            // Copy file
            FileHandler.copy(source, destination);

            log.info("Screenshot saved: " + destination.getAbsolutePath());
        } catch (IOException e) {
            log.error("Failed to capture screenshot: " + e.getMessage());
        }
    }
}
