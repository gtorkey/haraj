package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.loginPage;

public class updatePosts extends TestBase {
    loginPage login;
    String username = "Hambaka";
    String password = "847896";
    @Test
    public void UpdatePosts(){
        login = new loginPage(driver);
//        login.login(username,password);
        logger.pass("Settings clicked & add app page displayed");
        reporter("pass","Hello");
        reporter("info","Hello");
        System.out.println("Hello from Mars");
    }
}
