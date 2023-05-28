import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import java.util.*;  
import java.net.URL;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class SeleniumTest {
    private WebDriver driver;

    /*
    Tests done:
        - modify the browser options
    */
    @Before
    public void setup()  throws MalformedURLException  {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("incognito");
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
    }

    /*
    Tests done:
        - reading page title
        - multiple page test from array
    */
    @Test
    public void staticPageTests() {
        //we load the unicode-encoded string into this hashmap, then iterate it
         Map<String, String> pageUrlAndTitle = new HashMap<String, String>() {{
            put("https://hasznaltalma.hu/", "H\u00edrek, apr\u00F3hirdet\u00E9s \u00E9s minden, ami Apple - StillApple / HasznaltAlma.hu");
            put("https://hasznaltalma.hu/aprohirdetesek", "Apple apr\u00F3hirdet\u00E9sek - HasznaltAlma.hu");
            put("https://hasznaltalma.hu/hirek", "Apple H\u00EDrek / Tesztek / Pletyk\u00E1k / Tippek \u00E9s tr\u00FCkk\u00F6k - HasznaltAlma");
        }};

        PageBase page = new PageBase(this.driver);

        for (Map.Entry<String, String> entry : pageUrlAndTitle.entrySet()) {
            String url = entry.getKey();
            String title = entry.getValue();
            String pageTitle = page.getPageTitleOfSite(url);
            Assert.assertEquals(pageTitle, title);
        }
    }

    /*
    Tests done:
        - fill simple form and send
        - fill input: 2
        - send a form: 1
        - static page test: 1 (loading mainpage, getting username)
        - complex xpath: 4
        - log out 
        - reading dropdown (for logging out)
        - explicit wait (in the page methods)
    */
    @Test
    public void testLoginLogout() {
        //logging in
        LoginPage loginPage = new LoginPage(this.driver);
        MainPage mainPage = new MainPage(loginPage.login());
        Assert.assertTrue(mainPage.getGreetingTest().contains("Marcell"));

        //logging out
        mainPage.logout();
        Assert.assertTrue(mainPage.userIsLoggedOut());
    }

    /*
    Tests done: 
        - history test (browser back button)
        - JavaScript executor (for navigating back)
    For this test, I had to modify the container and give it more memory, because the pages kept crashing
    */
    @Test
    public void testNavigationStack() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.navigateToSubpage("news");
        mainPage.navigateBack();
        String pageTitle = mainPage.getTitle();
        Assert.assertEquals(pageTitle, "H\u00edrek, apr\u00F3hirdet\u00E9s \u00E9s minden, ami Apple - StillApple / HasznaltAlma.hu");
    }

    /*
    Tests done: 
        - download multiple files from a user-protected page
    */
    @Test 
    public void downloadFavoriteAdThumbnails() {
        LoginPage loginPage = new LoginPage(this.driver);
        FavoriteAdsPage favAdsPage = new FavoriteAdsPage(loginPage.login());
        try {
            favAdsPage.dowloadAdThumbNails();  
        }
        catch (Exception e) {
            System.out.println(e);
        }    
    }

    /*
    Tests done: 
        - form sending with user
    */
    @Test
    public void testEditProfile() {
        //please only use city names containing the letters of the English alphabet, since the encoding could cause errors
        String city = "Bucharest";
        LoginPage loginPage = new LoginPage(this.driver);
        ProfileEdit profileEditPage = new ProfileEdit(loginPage.login());
        profileEditPage.changeCity(city);
        ProfilePage profilePage = new ProfilePage(profileEditPage.getDriver());
        Assert.assertTrue(profilePage.returnUserData().contains(city));
    }

    @After
    public void close() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}
