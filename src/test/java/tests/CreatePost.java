package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddNewPostPage;
import pages.loginPage;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class CreatePost extends TestBase {
    loginPage login;
    AddNewPostPage post;
    SoftAssert soft = new SoftAssert();
    String username = "hambaka";
    String password = "847896";
    WebDriverWait wait;
    int postNumber = 4;
    int provinceNumber = 2;
    int cityNumber = 1;
    int neighborhoodIndex = 5;

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
        List<WebElement> posts = driver.findElements(By.cssSelector("div[class=\"flex-grow overflow-hidden\"]"));
        if (postNumber <= posts.size()) {
            String postText = posts.get(postNumber-1).getText();
            if (post.clickOnPost(postNumber)) {
                reporter("pass", "Successfully click on post number " + postNumber);
                reporter("info", "You clicked on: " + postText);
            }
        }
        else {
            int postCountSize = posts.size();
            reporter("fail", "Failed to click on post number " + postNumber + " and the total number of posts is " + postCountSize);
            return;
        }

        post.clickContinueButton();
        if (wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("h3[class=\"text-lg md:text-xl font-bold\"]")))).isDisplayed()) {
            reporter("pass", "Successfully click on continue button and redirected to the address page");
        } else {
            reporter("fail", "Failed to click on continue button ");
        }

        List<WebElement> provinces = driver.findElements(By.cssSelector("div[class=\"flex-grow overflow-hidden\"] a"));
        if (provinceNumber>0 && provinceNumber <= provinces.size()) {
            String provinceName = provinces.get(provinceNumber-1).getText();
            if (post.selectProvince(provinceNumber)) {
                reporter("pass", "Successfully select province number " + provinceNumber);
                reporter("info", "You selected: " + provinceName);
            }
        }else {
                int provinceCountSize = provinces.size();
                reporter("fail", "Failed to select province number " + provinceNumber + " and the total number of provinces is " + provinceCountSize);
                return;
        }
        List<WebElement> cities = driver.findElements(By.cssSelector("div[class=\"flex-grow overflow-hidden\"] a"));
            if (cityNumber>=0 && cityNumber <= cities.size()) {
                String cityName = cities.get(cityNumber-1).getText();
                if (post.selectCity(cityNumber)) {
                    reporter("pass", "Successfully select city number " + cityNumber);
                    reporter("info", "You selected: " + cityName);
                }
            } else {
                int cityCountSize = cities.size();
                reporter("fail", "Failed to select city number " + cityNumber + " and the total number of cities is " + cityCountSize);
                return;
            }

        AddNewPostPage.NeighborhoodSelectionStatus selectionStatus = post.selectNeighborhood(neighborhoodIndex);
        switch (selectionStatus) {
            case SUCCESS:
                reporter("pass", "Successfully select neighborhood number " + neighborhoodIndex + " and redirected to the map page");
                post.confirmMap();
                reporter("pass", "Successfully click on confirm map button and redirected to upload image page");
                break;
            case WARNING:
                reporter("info", "There is no Neighborhood or Map sections");
                break;
            case FAILURE:
                reporter("fail", "Failed to select neighborhood number " + neighborhoodIndex);
                return;
        }






        if (post.uploadImage()) {
            reporter("pass", "Successfully click on upload image button and redirected to details page");
        } else {
            reporter("fail", "Failed to upload image");
        }
    }
}
