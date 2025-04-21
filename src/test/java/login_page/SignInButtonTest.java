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

public class SignInButtonTest {
    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeClass
    public static void varInit() {
        driver = Driver.setUpDriver();
        driver.get(Urls.LOGIN_PAGE.getLink());
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public static void submitForm() {
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        email.sendKeys("john.doe@gmail.com");
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        password.sendKeys("Qwerty123");
        WebElement signInButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type=\"submit\"]")));
        signInButton.click();
        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type=file]")));
        Assert.assertEquals(driver.getCurrentUrl(), "https://qa-course-01.andersenlab.com/");
    }

    @AfterClass
    public static void closeDriver() {
        driver.quit();
    }
}