import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


class NewAdPage extends PageBase {

    private By fileDivLocator = By.xpath("//div[@id='drop']");
    private By fileFieldLocator = By.xpath("//input[@id='fileUpload']");
    private By titleField = By.xpath("//input[@id='ad_title']");
    private By descriptionField = By.xpath("//textarea[@name='description']");
    private By priceField = By.xpath("//input[@id='price']");
    private By sellerNameField = By.xpath("//input[@id='seller_name']");
    private By phoneNumberField = By.xpath("//input[@id='phone']");

    public NewAdPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://hasznaltalma.hu/ads/edit");
    }

    public void fileUpload(String filePath) {
        this.waitAndReturnElement(fileDivLocator).click();
        WebElement fileField = this.waitAndReturnElement(fileFieldLocator);
        fileField.sendKeys(filePath);
    } 

    public void createAd() {
        this.fileUpload("/home/selenium/mytests/test.image.jpg");
    }
}

