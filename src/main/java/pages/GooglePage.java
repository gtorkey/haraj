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
    private static final By searchResultLocator = By.xpath("//*[@class=\"oIk2Cb\"]//a");
    private static final By searchKeywordLocator = By.xpath("//ul[@role=\"listbox\"]/li");

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
        for (int i = 0; i <searchResults.size() ; i++) {
            String text = searchResults.get(i).getText();
            if (text.isEmpty())
                text = searchResults.get(i).getAttribute("textContent");
            searchResultText.add(text);
            System.out.println(text);
        }
        return searchResultText;
    }
    public List<String> searchKeyword(String keyword) {
        setTextElement(waitUntilElementToBevisible(searchBoxLocator), keyword);
        List<WebElement> searchResults = driver.findElements(searchKeywordLocator);
        List<String> searchKeywordText = new ArrayList<>();
        for (int i = 0; i <searchResults.size() ; i++) {
            String text = searchResults.get(i).getText();
            if (text.isEmpty())
                text = searchResults.get(i).getAttribute("textContent");
            searchKeywordText.add(text);
            System.out.println(text);
        }
        waitUntilElementToBeClickable(searchBoxLocator).submit();
        String pageTitle= driver.getTitle();
        assert pageTitle.contains(keyword);
        return searchKeywordText;
    }
}
