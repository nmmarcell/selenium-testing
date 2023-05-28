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


class ProfilePage extends PageBase {

    private By tableLocator = By.xpath("//table");

    public ProfilePage(WebDriver driver) {
        super(driver);
        this.driver.get("https://hasznaltalma.hu/users/profile");
    }

    public String returnUserData() {
        return this.waitAndReturnElement(tableLocator).getText();
    }
}

