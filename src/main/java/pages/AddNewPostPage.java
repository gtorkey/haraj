package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AddNewPostPage extends Base {

    private static final By addNewPostLocator = By.cssSelector("button[data-testid=\"add-post-button\"]");
    private static final By postsLocator = By.cssSelector("div[class=\"flex-grow overflow-hidden\"]");
    private static final By agreeLocator  = By.cssSelector("input[data-testid=\"step-two-agreement-checkbox\"]");
    private static final By continueBtnLocator  = By.cssSelector("button[data-testid=\"step-two-resume\"]");
    private static final By provincesLocator  = By.cssSelector("div[class=\"flex-grow overflow-hidden\"] a");
    private static final By citiesLocator  = By.cssSelector("div[class=\"flex-grow overflow-hidden\"] a");
    private static final By neighborhoods = By.cssSelector("div[class=\"flex-grow overflow-hidden\"] a");
    private static final By neighborhoodLocator= By.cssSelector("h3[class=\"text-lg md:text-xl font-bold\"]");
    private static final By mapTextLocator= By.cssSelector("h2[class=\"text-xl md:text-2xl font-bold mb-4 font-semibold\"]");
    private static final By confirmMapLocator= By.cssSelector("button[data-testid=\"mapbox-location-confirm\"]");
    private static final By uploadImageLocator= By.cssSelector("button[data-testid=\"upload-box-others\"]");
    private static final By uploadBtnLocator= By.cssSelector("button[data-testid=\"step-four-resume\"]");






    public AddNewPostPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void goToAddPostPage() {
        click(waitUntilElementToBeClickable(addNewPostLocator));
    }



    public boolean clickOnPost(int postNumber) {
        List<WebElement> posts = driver.findElements(postsLocator);
        if (postNumber <= posts.size()) {
            click(posts.get(postNumber));
            return true;
        }
        return false;
    }
    public void clickContinueButton()  {
        List<WebElement> agrees = driver.findElements(agreeLocator);
        for (WebElement agree : agrees) {
                click(agree);
        }
        click(waitUntilElementToBeClickable(continueBtnLocator));
    }
    public boolean selectProvince(int provinceIndex) throws InterruptedException {
        List<WebElement> provinces = driver.findElements(provincesLocator);
        if (provinceIndex>=0 && provinceIndex <= provinces.size()) {
            click(provinces.get(provinceIndex-1));
            return true;
        }
        return false;
    }

    // Method to select a city by index
    public boolean selectCity(int cityIndex) throws InterruptedException {
        List<WebElement> cities = driver.findElements(citiesLocator);
        if (cityIndex > 0 && cityIndex < cities.size()) {
            click(cities.get(cityIndex-1));
            return true;
        }
        return false;
    }

    // Method to select a neighborhood by index
    public enum NeighborhoodSelectionStatus {
        SUCCESS,
        FAILURE,
        WARNING
    }
    public NeighborhoodSelectionStatus selectNeighborhood(int neighborhoodIndex) throws InterruptedException {
        if (driver.findElement(neighborhoodLocator).getText().contains("Neighborhood"))
        {
            List<WebElement> neighborhoodsList = driver.findElements(neighborhoods);
            if (neighborhoodIndex > 0 && neighborhoodIndex < neighborhoodsList.size()) {
                click(neighborhoodsList.get(neighborhoodIndex - 1));
                return NeighborhoodSelectionStatus.SUCCESS;
            }else {
                return NeighborhoodSelectionStatus.FAILURE;
            }
        }else {
            return NeighborhoodSelectionStatus.WARNING;
        }

    }



    // Method to confirm map and click on confirmation button
    public boolean confirmMap() {
        try {
            soft.assertTrue(driver.findElement(mapTextLocator).isDisplayed());
            click(waitUntilElementToBeClickable(confirmMapLocator));
            return true;
        } catch (Exception e) {
            System.out.println("Map locator not found.");
            return false;
        }
    }

    // Method to upload an image
    public boolean uploadImage() throws InterruptedException {
        try {
            soft.assertTrue(driver.findElement(By.cssSelector("h3[class=\"text-lg md:text-xl font-bold\"]")).getText().contains("Upload images"));
            String path = "C:\\Users\\qaahm\\Videos\\Captures\\test.png";
            WebElement uploadInput = waitUntilElementToBeClickable(uploadImageLocator);
            uploadInput.sendKeys(path);
            click(waitUntilElementToBeClickable(uploadBtnLocator));
            return true;
        } catch (Exception e) {
            System.out.println("Upload image locator not found.");
            return false;
        }
    }



}
