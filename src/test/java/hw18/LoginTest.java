package hw18;

import hw18.utils.Credentials;
import hw18.utils.Urls;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static LoginPage loginPage;

    @BeforeClass
    @Parameters({"browser"})
    public void varInit(String browser) {
        switch (browser) {
            case "Safari" -> {
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
            }
            case "Chrome" -> {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            case "Firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
        }
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1)
    public void titleTest() {
        loginPage.openLoginPage();
        Assert.assertEquals(driver.getTitle(), "QA Course 01");
    }

    @Test(priority = 2)
    public static void emailPlaceholderTest() {
        Assert.assertEquals(loginPage.getEmailField().getDomAttribute("placeholder"), "Enter email");
    }

    @Test(priority = 3)
    public void emptyEmailTest() {
        loginPage.enterEmail("");
        Assert.assertEquals(loginPage.getRequiredText(), "Required");
    }

    @Test(priority = 4)
    public void leadingSpacesTest() {
        driver.navigate().refresh();
        loginPage.enterEmail("   " + Credentials.REGISTERED_USER_LOGIN.getCredential());
        Assert.assertEquals(loginPage.getInvalidEmailText(), "Invalid email address");
    }

    @Test(priority = 5)
    public void trailingSpacesTest() {
        driver.navigate().refresh();
        loginPage.enterEmail(Credentials.REGISTERED_USER_LOGIN.getCredential() + "   ");
        Assert.assertEquals(loginPage.getInvalidEmailText(), "Invalid email address");
    }

    @Test(priority = 6)
    public void midSpacesTest() {
        driver.navigate().refresh();
        loginPage.enterEmail(Credentials.LOGIN_MID_SPACES.getCredential());
        Assert.assertEquals(loginPage.getInvalidEmailText(), "Invalid email address");
    }

    @Test(priority = 8)
    public void emptyPasswordTest() {
        driver.navigate().refresh();
        loginPage.enterPassword("");
        Assert.assertEquals(loginPage.getRequiredText(), "Required");
    }

    @Test(priority = 9)
    public static void passwordPlaceholderTest() {
        driver.navigate().refresh();
        Assert.assertEquals(LoginPage.getPasswordField().getDomAttribute("placeholder"), "Enter password");
    }

    @Test(priority = 10)
    public void registrationLinkTest() {
        driver.navigate().refresh();
        loginPage.clickRegistrationLink();
        Assert.assertEquals(driver.getCurrentUrl(), Urls.REGISTRATION_PAGE.getLink());
    }

    @AfterClass
    public static void closeDriver() {
        driver.quit();
    }
}