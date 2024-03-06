package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {
    private static Logger log = Logger.getLogger(TestBase.class.getName());
    WebDriver driver;
    @BeforeMethod
    public void setupBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://haraj.com.sa/");
    }
    @AfterMethod
    public void quitBrowser() throws InterruptedException {
        Thread.sleep(10000);
        driver.quit();
    }
}
