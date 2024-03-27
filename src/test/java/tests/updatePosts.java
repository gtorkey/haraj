package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.loginPage;

public class updatePosts extends TestBase {
    loginPage login;
    SoftAssert soft = new SoftAssert();
    String username = "hambaka";
    String password = "847896";

    @Test
    public void UpdatePosts() throws InterruptedException {
        login = new loginPage(driver);
        login.login(username,password);
        login.verifyLogin();
        reporter("pass","Successfully logged in");
        login.goToProfile();
        reporter("pass","Successfully go to profile");
        logger.info("Numbers of Posts is : "+login.postNumbers());
        for (int i = 1; i < login.postNumbers(); i++) {
            login.clickOnSinglePost(i);
            login.updateSinglePost();
            driver.navigate().back();
            System.out.println("number is"+i);
        }
        System.out.println("Hello from Mars 2");
        logger.pass("Successfully logged in");
    }
}
