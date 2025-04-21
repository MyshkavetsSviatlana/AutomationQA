package login_page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Driver;
import utils.Urls;

import java.time.Duration;

public class RegistrationLinkTest {
    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeClass
    public static void varInit() {
        driver = Driver.setUpDriver();
        driver.get(Urls.LOGIN_PAGE.getLink());
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public static void redirectionTest() {
        WebElement registrationLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@ class=\"mt-3 block cursor-pointer hover:underline\"]")));
        registrationLink.click();
        WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@ type=\"submit\"]")));
        Assert.assertEquals(driver.getCurrentUrl(), Urls.REGISTRATION_PAGE.url);
    }

    @AfterClass
    public static void closeDriver() {
        driver.quit();
    }
}