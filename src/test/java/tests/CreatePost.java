package tests;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddNewPostPage;
import pages.loginPage;

import java.time.Duration;

public class CreatePost extends TestBase {
    loginPage login;
    AddNewPostPage post;
    SoftAssert soft = new SoftAssert();
    String username = "hambaka";
    String password = "847896";
    WebDriverWait wait;

    @Test
    public void createPost() throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        login = new loginPage(driver);
        post = new AddNewPostPage(driver);
        login.login(username, password);
        login.verifyLogin();
        reporter("pass", "Successfully logged in");
        login.changeLang();
        reporter("pass", "Successfully change Language");
        post.goToAddPostPage();
        reporter("pass", "Successfully go to add post page");

    }
}
