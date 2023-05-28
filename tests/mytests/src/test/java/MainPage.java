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


class MainPage extends PageBase {
    
    //there are usually two spans containing the same elements in the page structure: one is visible at a large, the other is visible at a small screen size
    //we select the large screen size one by specifying the div that contains our targeted elements by its classes.
    private By greetingSpanSelector = By.xpath("//div[@id='main-header' and contains(@class, 'hidden-sm')]//span[contains(@class, 'arrow-down')]");
    private By dropdownMenuSelector = By.xpath("//div[contains(@class, 'text-right')]//div[contains(@class, 'panel-user')]");
    private By logoutButtonSelector = By.xpath("//div[contains(@class, 'text-right')]//a[@href='users/logout']");
    private By loginRegisterSpanSelector = By.xpath("//div[contains(@class, 'text-right')]/div[@id='main-header-first']/aside");

    private By newsPageSelector = By.xpath("//div[contains(@class, 'hidden-xs')]//a[@href='hirek']");
    private By adPageSelector = By.xpath("//div[contains(@class, 'hidden-xs')]//a[contains(@href, 'aprohirdetesek')]");

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://hasznaltalma.hu/");
    }

    public String getGreetingTest() {
        return this.waitAndReturnElement(greetingSpanSelector).getText();
    }

    public void logout() {
        this.waitAndReturnElement(dropdownMenuSelector).click();
        this.waitAndReturnElement(logoutButtonSelector).click();
    }

    public boolean userIsLoggedOut() {
        WebElement loginRegisterSpan = this.waitAndReturnElement(loginRegisterSpanSelector);
        String insideText = loginRegisterSpan.getText();
        return insideText.contains("Bel\u00E9p\u00E9s");
    }

    public void navigateToSubpage(String page) {
        switch(page) {
            case "news": 
                this.waitAndReturnElement(newsPageSelector).click();
                break;
            case "ads": 
                this.waitAndReturnElement(adPageSelector).click();
                break;
        }
        this.waitAndReturnElement(bodySelector);
    }

}
 