package tests;

import org.openqa.selenium.By;
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
        login.login(username,password);
        System.out.println("Hello from Mars");
        Assert.assertEquals(login.profileName(),username.toLowerCase());
    }


}
