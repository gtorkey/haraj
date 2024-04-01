package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AddNewPostPage extends Base {

    private static final By addNewPostLocator = By.cssSelector("button[data-testid=\"add-post-button\"]");
    private static final By saleOrWaiverCar = By.cssSelector("div[data-testid=\"post-type-1-label\"]");
    private static final By partsOrAccessoriesCar = By.cssSelector("div[data-testid=\"post-type-5-label\"]");
    private static final By truckAndHeavy = By.cssSelector("div[data-testid=\"post-type-60-label\"]");
    private static final By Motorcycle = By.cssSelector("div[data-testid=\"post-type-4-label\"]");
    private static final By saleRealState = By.cssSelector("div[data-testid=\"post-type-6-label\"]");
    private static final By rentRealState = By.cssSelector("div[data-testid=\"post-type-7-label\"]");
    private static final By saleDevice = By.cssSelector("div[data-testid=\"post-type-10-label\"]");
    private static final By saleFurniture = By.cssSelector("div[data-testid=\"post-type-11-label\"]");
    private static final By saleLivestock  = By.cssSelector("div[data-testid=\"post-type-450-label\"]");
    private static final By notListed  = By.cssSelector("div[data-testid=\"post-type-8-label\"]");
    private static final By addService  = By.cssSelector("div[data-testid=\"post-type-458-label\"]");
    private static final By requestAware  = By.cssSelector("div[data-testid=\"post-type-9-label\"]");
    private static final By agreeLocator  = By.cssSelector("input[data-testid=\"step-two-agreement-checkbox\"] ");
    private static final By continueBtnLocator  = By.cssSelector("button[data-testid=\"step-two-resume\"]");

    public AddNewPostPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void goToAddPostPage() {
        click(waitUntilElementToBeClickable(addNewPostLocator));
    }
}
