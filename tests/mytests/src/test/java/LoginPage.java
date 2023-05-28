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


class LoginPage extends PageBase {

    private By usernameField = By.xpath("//input[@name='email']");
    private By passwordField = By.xpath("//input[@name='password']");
    private By submitButton = By.xpath("//div[contains(@class, 'row form-group')]/div/input[contains(@class, 'btn-lg')]");

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://hasznaltalma.hu/users/login");
    }

    public WebDriver login() {
        String username = "y5g10z";
        String password = "sqat2023";
        this.waitAndReturnElement(usernameField).sendKeys(username + "@inf.elte.hu");
        this.waitAndReturnElement(passwordField).sendKeys(password);
        this.waitAndReturnElement(submitButton).click();

        //we pass back the driver, so it can be used for creating any site object we want
        return this.driver;
    }
}

