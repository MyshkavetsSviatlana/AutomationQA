package hw15.login_page;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Driver;
import hw15.utils.Urls;

import java.time.Duration;

public class PasswordFieldTest {
    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeClass
    public static void varInit() {
        driver = Driver.setUpDriver();
        driver.get(Urls.LOGIN_PAGE.getLink());
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public static void alertFewCharsTest() {
        driver.navigate().refresh();
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        password.sendKeys("Qwerty1");
        password.submit();
        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Min')]")));
        Assert.assertEquals("Minimum 8 characters", alert.getText());
    }

    @Test
    public static void alertExcessiveCharsTest() {
        driver.navigate().refresh();
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        password.sendKeys("Qwerty123456789101112");
        password.submit();
        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Max')]")));
        Assert.assertEquals("Maximum 20 characters", alert.getText());
    }

    @AfterClass
    public static void closeDriver() {
        driver.quit();
    }
}