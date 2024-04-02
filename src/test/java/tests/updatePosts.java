package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.loginPage;

import java.time.Duration;

public class updatePosts extends TestBase {
    loginPage login;
    SoftAssert soft = new SoftAssert();
    String username = "hambaka";
    String password = "847896";
    WebDriverWait wait;

    @Test
    public void UpdatePosts() throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        login = new loginPage(driver);
        login.login(username,password);
        login.verifyLogin();
        reporter("pass","Successfully logged in");
        login.goToProfile();
        reporter("pass","Successfully go to profile");
        login.changeLang();
        reporter("pass","Successfully change Language");
        logger.info("Numbers of Posts is : "+login.postNumbers());
        for (int i = 1; i <= login.postNumbers(); i++) {
            login.clickOnSinglePost(i);
            login.updateSinglePost();

            try {
                wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class=\"ct-row\"][1]/div[2]//div[@class=\"ct-toast  ct-toast-error\"]//div[@class=\"ct-text-group\"]/div ")))).isDisplayed();
                reporter("fail","Post number "+i+" is not updated "+"Last update time is : "+login.getLastUpdateTime());
                soft.fail("Post number "+i+" is not updated ");
            }catch (Exception e)
            {
                reporter("pass","Post number "+i+" is updated");
            }
            driver.navigate().back();

            System.out.println("number is "+ i);
        }
        /*System.out.println("Hello from Mars 2");
        logger.pass("Successfully logged in");*/

    }
}
