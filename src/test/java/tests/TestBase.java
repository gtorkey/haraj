package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
public class TestBase {
    protected static ExtentReports extent;
    protected static ExtentSparkReporter reporter;
    protected static ExtentTest logger;

    static WebDriver driver;
    public static void reporter(String status, String stepDetail) {

        //ExtentTest logger = null;
        String base64Screenshot;
        try {
            base64Screenshot = HelperClass.capture(driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (status.equalsIgnoreCase("pass")) {
            logger.pass(stepDetail, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
        } else if (status.equalsIgnoreCase("fail")) {
            logger.fail(stepDetail, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
        } else if (status.equalsIgnoreCase("info")) {
            logger.info(stepDetail);
        } else if (status.equalsIgnoreCase("Warning")) {
            logger.warning(stepDetail);
        }

    }

    @BeforeSuite
    public void setUpSuite() {
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("target/extent-report.html");
        extent.attachReporter(spark);
    }

    @BeforeMethod
    public void setUp() {
        reporter = new ExtentSparkReporter("target/extent-report.html");
        extent.attachReporter(reporter);

        logger = extent.createTest("Haraj");

        // Initialize the WebDriver instance
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://haraj.com.sa/");
        logger.pass("User logged in, Home page loaded successfully");

    }

    @AfterMethod
    public void tearDown(ITestResult result) throws InterruptedException, IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Capture screenshot on test failure
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Save the screenshot to a file
            String screenshotPath = "target/screenshots/" + result.getName() + ".png";
            FileUtils.copyFile(screenshotFile, new File(screenshotPath));

            // Attach the screenshot to the report
            logger.fail("Test Failed. See screenshot below:");
            logger.fail(result.getThrowable());
            logger.addScreenCaptureFromPath(screenshotPath);
        }

        Thread.sleep(3000);
        driver.quit();
    }


    @AfterSuite
    public void tearDownSuite() {
        // Flush the ExtentReports instance to generate the report
        extent.flush();
    }
}