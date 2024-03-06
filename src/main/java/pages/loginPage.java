package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class loginPage extends Base{
    private final By loginLinkLocator = By.cssSelector("button[data-testid=\"login-link\"]");
    private final By userNameLocator = By.id("username");
    private final By submitUserNameLocator = By.cssSelector("button[data-testid=\"auth_submit_username\"]");
    private final By passwordLocator = By.id("password");
    private final By submitLoginLocator = By.cssSelector("button[data-testid=\"auth_submit_login\"]");
    private final By profileNameLocator =By.cssSelector("div[class=\"max-w-[12ch] overflow-hidden text-ellipsis pt-1 text-lg\"]");

    public loginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void login(String username,String password){
        click(waitUntilElementToBeClickable(loginLinkLocator));
        setTextElement(waitUntilElementToBeClickable(userNameLocator), username);
        click(waitUntilElementToBeClickable(submitUserNameLocator));
        setTextElement(waitUntilElementToBeClickable(passwordLocator), password);
        click(waitUntilElementToBeClickable(submitLoginLocator));
    }
    public String profileName(){
        return waitUntilElementToBevisible(profileNameLocator).getText();
    }

}
