package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import pages.GooglePage;

import java.sql.Driver;
import java.util.List;

public class GoogleSearch extends TestBase {

    GooglePage googlePage;

    @Override
    public void setUp() {
        executeBeforeMethod = false;
        super.setUp();
    }

    String query = "selenium";

    @Test
    public void testGoogleSearch() throws InterruptedException {
        setUp();
        driver.get("https://www.google.com");
        googlePage = new GooglePage(driver);
        googlePage.search(query);
        reporter("pass","Google search for " + query + " is successful");
        List<String> results = googlePage.searchResult();
        for (String result : results) {
            reporter("info",result);
        }
    }
}
