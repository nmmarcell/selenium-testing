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

class PageBase {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected By bodySelector = By.xpath("//body");
    
    public PageBase(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public WebDriver getDriver() {
        return this.driver;
    }   

    protected WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }
    
    public String getBodyText() {
        WebElement bodyElement = this.waitAndReturnElement(By.tagName("body"));
        return bodyElement.getText();
    }
   
    //method for getting a site and returning its title
    public String getPageTitleOfSite(String url) {
        this.driver.get(url);
        return driver.getTitle();
    }

    //method for returning the current site's title, whichever it may be 
    public String getTitle() {
        return this.driver.getTitle();
    }

    public void navigateBack() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.history.back();");
    }

    public void goTo(String url) {
        this.driver.navigate().to(url);
    }
}
