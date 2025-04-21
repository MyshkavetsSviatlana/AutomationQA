package login_page;

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
import utils.Urls;

import java.time.Duration;

public class EmailField {
    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeClass
    public static void varInit() {
        driver = Driver.setUpDriver();
        driver.get(Urls.LOGIN_PAGE.getLink());
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public static void checkEmailPlaceholder() {
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        Assert.assertEquals("Enter email", email.getDomAttribute("placeholder"));
    }

    @Test
    public static void checkAlertEmptyField() {
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        email.submit();
        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Required')]")));
        Assert.assertEquals("Required", alert.getText());
    }

    @AfterClass
    public static void closeDriver() {
        driver.quit();
    }
}