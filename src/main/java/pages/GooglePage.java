package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class GooglePage extends Base{

    private static final By searchBoxLocator = By.id("APjFqb");
    private static final By searchResultLocator = By.cssSelector("h3[class=\"LC20lb MBeuO DKV0Md\"]");

    public GooglePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void search(String text) {
        setTextElement(waitUntilElementToBevisible(searchBoxLocator), text);
        waitUntilElementToBeClickable(searchBoxLocator).submit();
        String pageTitle= driver.getTitle();
        assert pageTitle.contains(text);
    }
    public List<String> searchResult() {
        List<WebElement> searchResults = driver.findElements(searchResultLocator);
        List<String> searchResultText = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            String text = searchResults.get(i).getText();
            if (text.isEmpty())
                text = searchResults.get(i).getAttribute("textContent");
            searchResultText.add(text);
            System.out.println(text);
        }
        return searchResultText;
    }
}
