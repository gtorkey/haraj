package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

public class loginPage extends Base {
    public String username = "hambaka";
    public String password = "847896";

    private final By loginLinkLocator = By.cssSelector("button[data-testid=\"login-link\"]");
    private final By userNameLocator = By.id("username");
    private final By submitUserNameLocator = By.cssSelector("button[data-testid=\"auth_submit_username\"]");
    private final By passwordLocator = By.id("password");
    private final By submitLoginLocator = By.cssSelector("button[data-testid=\"auth_submit_login\"]");
    public final By profileNameLocator = By.xpath("//div[contains(text(), '" + username + "')]");
    public final By accountProfileLocator = By.xpath("//a[@data-testid=\"store\"]");
    public final By updateButton = By.xpath("//button[@data-testid=\"update-button\"]");


    public loginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
        click(waitUntilElementToBeClickable(loginLinkLocator));
        setTextElement(waitUntilElementToBeClickable(userNameLocator), username);
        click(waitUntilElementToBeClickable(submitUserNameLocator));
        setTextElement(waitUntilElementToBeClickable(passwordLocator), password);
        click(waitUntilElementToBeClickable(submitLoginLocator));
    }
    public void verifyLogin(){
        soft.assertTrue(waitUntilElementToBevisible(profileNameLocator).isDisplayed());
    }
    public void goToProfile(){
        click(waitUntilElementToBeClickable(profileNameLocator));
        click(waitUntilElementToBeClickable(accountProfileLocator));

    }
    public int postNumbers(){
        return driver.findElements(By.xpath("//div[@id=\"postsList\"]//div[@data-testid=\"post-item\"]")).size();
    }
    public void clickOnSinglePost(int indexNum){
        WebElement singlePost = driver.findElement(By.xpath("//div[@id=\"postsList\"]//div[@data-testid=\"post-item\"]["+indexNum+"]"));
        click(singlePost);
    }
    public void updateSinglePost(){
        click(updateButton);
    }




}
