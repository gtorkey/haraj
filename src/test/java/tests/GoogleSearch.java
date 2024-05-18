package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
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

    String query;
    @BeforeClass
    @Parameters({"query"})
    public void setQuery(String query) {
        this.query = query;
    }
    @Test
    public void testGoogleSearchKeyword() throws InterruptedException {
        setUp();
        driver.get("https://www.google.com");
        googlePage = new GooglePage(driver);
        reporter("pass","Google search for " + query + " is successful");
        List<String> keywords = googlePage.searchKeyword(query);
        for (String keyword : keywords) {
            reporter("info",keyword);
        }
        List<String> results = googlePage.searchResult();
        for (String result : results) {
            reporter("info",result);
        }

    }
}
