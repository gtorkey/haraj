package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class copyPost extends Base{

    private static final By postPriceLocator = By.cssSelector("div[class=\"w-[50%] overflow-hidden text-ellipsis whitespace-nowrap  mr-[-3px] flex items-center text-[#474855] dark:text-[#b1b1b1]\"] span");
    private static final By postLinkLocator = By.xpath("//a[@data-testid=\"post-title-link\"][1]/h3");
    private static final By titlePostLocator = By.xpath("//h1[@data-testid=\"post_title\"]");
    private static final By addressPostLocator = By.className("city");
    private static final By articlePostLocator = By.cssSelector("article[data-testid='post-article']");
    private static final By contactBtnLocator = By.cssSelector("button[data-testid=\"post-contact\"]");
    private static final By mobNumberLocator = By.cssSelector("a[data-testid=\"contact_mobile\"]");
    private static final By exitLocator = By.cssSelector("svg[data-icon=\"times\"]");
//    private static final By mobNumberLocator = By.cssSelector("a[data-testid=\"contact_mobile\"] div:nth-child(2)]");


    public copyPost(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public double getPostPrice() {
        String price = driver.findElement(postPriceLocator).getText();
        return Double.parseDouble(price.replaceAll("[^0-9.]", ""));
    }
    public String getTitlePost() {
        click(waitUntilElementToBeClickable(postLinkLocator));
        return driver.findElement(titlePostLocator).getText();
    }
    public String getAddressPost() {
        return driver.findElement(addressPostLocator).getText();
    }

    public String[] getArticlePost() {
        String text = driver.findElement(articlePostLocator).getText();
        String[] parts = text.split("\n", 2);

        String postTitle = parts[0].trim();
        String postDescription = "";

        if (parts.length > 1) {
            postDescription = parts[1].trim();
        }

        return new String[]{postTitle, postDescription};
    }

    public String getMobileNumber() throws InterruptedException {
        click(waitUntilElementToBeClickable(contactBtnLocator));
        Thread.sleep(2000);
        String number =  waitUntilElementToBevisible(mobNumberLocator).getText();
        click(waitUntilElementToBeClickable(exitLocator));
        return number;
    }




}
