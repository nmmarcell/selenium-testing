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


class ProfileEdit extends PageBase {

    private By cityFieldLocator = By.xpath("//input[@name='city']");
    private By saveButtonLocator = By.xpath("//button[@name='end']");

    public ProfileEdit(WebDriver driver) {
        super(driver);
        this.driver.get("https://hasznaltalma.hu/users/edit_profile");
    }

    public void changeCity(String city) {
        WebElement cityField = this.waitAndReturnElement(cityFieldLocator);
        cityField.clear();
        cityField.sendKeys(city);

        WebElement saveButton = waitAndReturnElement(saveButtonLocator);
        //I had to use JS because the elements were overlapping and Selenium couldn't click the button by itself
        JavascriptExecutor executor = (JavascriptExecutor) this.driver; 
        executor.executeScript("arguments[0].click();", saveButton);
    }
}

