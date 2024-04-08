package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.AddNewPostPage;
import pages.copyPost;
import pages.loginPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class TestCopyPost extends TestBase {

    @Test
    public void testCopyPost() throws InterruptedException {
        copyPost copyPost = new copyPost(driver);
        loginPage login = new loginPage(driver);
        AddNewPostPage post = new AddNewPostPage(driver);

        // Login
        login.login("hambaka", "847896");
        login.verifyLogin();
        reporter("pass", "Successfully logged in");

        // Go to profile
        login.goToProfile();
        reporter("pass", "Successfully navigated to profile");

        // Change language
        login.changeLang();
        reporter("pass", "Successfully changed language");

        // Get post details
        int postPrice = copyPost.getPostPrice();
        String title = copyPost.getTitlePost();
        String address = copyPost.getAddressPost();
        String[] articlePost = copyPost.getArticlePost();
        String postType = articlePost[0];
        String postDescription = articlePost[1];
        String mobileNumber = copyPost.getMobileNumber();

        // Output post details
        reporter("info", "Post price is: " + postPrice);
        reporter("info", "Post title is: " + title);
        reporter("info", "Post address is: " + address);
        reporter("info", "Post type is: " + postType);
        reporter("info", "Post description is: " + postDescription);
        reporter("info", "Mobile number is: " + mobileNumber);

        copyPost.goToHomePage();
        // Go to add post page
        post.goToAddPostPage();
        reporter("pass", "Successfully navigated to add post page");

        // Find and click on post with matching type
        boolean postFound = clickOnPostWithMatchingType(post, postType);
        if (!postFound) {
            reporter("fail", "Failed to find the post with type: " + postType);
            return;
        }
        reporter("pass", "Successfully found the post with type: " + postType);

        // Continue to address page
        post.clickContinueButton();
        reporter("pass", "Successfully clicked on continue button and redirected to the address page");

        // Select city based on address
        boolean citySelected = selectCityBasedOnAddress(post, "Jdh");
        if (!citySelected) {
            reporter("fail", "Failed to select city based on address");
            return;
        }

        // Select neighborhood
        int neighborhoodIndex = 5; // Assuming this is a predefined index
        AddNewPostPage.NeighborhoodSelectionStatus selectionStatus = post.selectNeighborhood(neighborhoodIndex);
        handleNeighborhoodSelectionStatus(selectionStatus, neighborhoodIndex);

        // Upload image and set post details
        if (post.uploadImage(2, "C:\\Users\\qaahm\\Videos\\Captures\\test.png")) {
            reporter("pass", "Successfully uploaded image");
        } else {
            reporter("fail", "Failed to upload image");
            return;
        }
        reporter("info", "Your address: " + post.printSelectedAddress());

        post.setPostTitle(title);
        reporter("pass", "Successfully entered post title");

        if (mobileNumber.isEmpty()) {
            post.hidePhoneNumber();
            reporter("pass", "Successfully hidden phone number");
        } else {
            post.setPhoneNumber(mobileNumber);
            reporter("pass", "Successfully set phone number");
        }

        if (postPrice == 0) {
            reporter("info", "There is no price!!");
        } else {
            post.setPrice(postPrice);
            reporter("pass", "Successfully set price");
        }

        post.setDetails(postDescription);
        reporter("pass", "Successfully entered post details");

        // Create post
        post.createPost();
        reporter("pass", "Successfully duplicated the post");
    }

    private boolean clickOnPostWithMatchingType(AddNewPostPage post, String postType) {
        List<WebElement> posts = driver.findElements(By.cssSelector("div[class=\"flex-grow overflow-hidden\"]"));
        String[] postTypeWords = postType.split("\\s+");
        for (int i = 0; i < posts.size(); i++) {
            boolean postTypeFound = false;
            for (String word : postTypeWords) {
                if (!posts.get(i).getText().contains(word)) {
                    postTypeFound = true;
                    break;
                }
            }
            if (postTypeFound) {
                post.clickOnPost(i + 1);
                return true;
            }
        }
        return false;
    }


    private boolean selectCityBasedOnAddress(AddNewPostPage post, String addressKeyword) throws InterruptedException {
        Map<String, String> addressMapping = new HashMap<>();
        addressMapping.put("Jeddah", "Jdh");
        addressMapping.put("Makkah", "Mkh");

        List<WebElement> provinces = driver.findElements(By.cssSelector("div[class=\"flex-grow overflow-hidden\"] a"));
        for (int i = 0; i < provinces.size(); i++) {
            post.selectProvince(i + 1);
            Thread.sleep(1000);
            List<WebElement> cities = driver.findElements(By.cssSelector("div[class=\"flex-grow overflow-hidden\"] a"));

            for (int j = 0; j < cities.size(); j++) {
                if (cities.get(j).getText().contains(addressKeyword)) {
                    post.selectCity(j + 1);
                    return true;
                }
            }
            copyPost.navigateBack();
            Thread.sleep(1000);
        }
        return false;
    }

    private void handleNeighborhoodSelectionStatus(AddNewPostPage.NeighborhoodSelectionStatus status, int neighborhoodIndex) throws InterruptedException {
        switch (status) {
            case SUCCESS:
                reporter("pass", "Successfully selected neighborhood number " + neighborhoodIndex + " and redirected to the map page");
                break;
            case WARNING:
                reporter("info", "There is no Neighborhood or Map sections");
                break;
            case FAILURE:
                reporter("fail", "Failed to select neighborhood number " + neighborhoodIndex);
                break;
        }
    }
}
