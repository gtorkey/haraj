package tests;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.AddNewPostPage;
import pages.copyPost;
import pages.loginPage;

import java.time.Duration;

public class TestCopyPost extends TestBase{

    copyPost copyPost;
    WebDriverWait wait;
    loginPage login;
    AddNewPostPage post;
    String username = "hambaka";
    String password = "847896";
    @Test
    public void testCopyPost() throws InterruptedException {
        copyPost = new copyPost(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        login = new loginPage(driver);
        post = new AddNewPostPage(driver);

        login.login(username, password);
        login.verifyLogin();
        reporter("pass", "Successfully logged in");

        login.goToProfile();
        reporter("pass","Successfully go to profile");

        login.changeLang();
        reporter("pass","Successfully change Language");

        double postPrice = copyPost.getPostPrice();
        reporter("pass","Successfully get post price");
        reporter("info","Post price is: " + postPrice);

        String title = copyPost.getTitlePost();
        reporter("pass","Successfully get title post");
        reporter("info","Post title is: " + title);

        String address = copyPost.getAddressPost();
        reporter("pass","Successfully get address post");
        reporter("info","Post address is: " + address);

        String postType = copyPost.getArticlePost()[0];
        reporter("pass","Successfully get article post");
        reporter("info","Post type is: " + postType);

        String postDescription = copyPost.getArticlePost()[1];
        reporter("pass","Successfully get article post description");
        reporter("info","Post description is: " + postDescription);

        String mobileNumber = copyPost.getMobileNumber();
        reporter("pass","Successfully get mobile number");
        reporter("info","Mobile number is: " + mobileNumber);

    }
}
