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

import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

class FavoriteAdsPage extends PageBase {

    public FavoriteAdsPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://hasznaltalma.hu/favorites");
    }

    public void dowloadAdThumbNails() throws MalformedURLException, IOException {
        for (int i = 1; i <= 4; i++) {
            String thumbnailSelectorString = String.format("(//div[contains(@class, 'img-container')]//a)[%d]", i);
            By currentThumbnailSelector = By.xpath(thumbnailSelectorString);
            WebElement imageDiv = this.waitAndReturnElement(currentThumbnailSelector);
            String cssPath = imageDiv.getCssValue("background-image");
            String imgpath = cssPath.substring(5, cssPath.length() - 2);
            System.out.println(imgpath);
            URL url = new URL(imgpath);
            BufferedImage image = ImageIO.read(url);
            ImageIO.write(image, "jpg", new File("images/" + i + ".jpg"));
        }
    }
}
 