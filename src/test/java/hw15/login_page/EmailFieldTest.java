package hw15.login_page;

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

import static org.testng.Assert.assertEquals;

public class EmailFieldTest {
    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeClass
    public static void varInit() {
        driver = Driver.setUpDriver();
        driver.get(Urls.LOGIN_PAGE.getLink());
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public static void emailPlaceholderTest() {
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        assertEquals(email.getDomAttribute("placeholder"), "Enter email");
    }

    @Test
    public static void alertEmptyFieldTest() {
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        email.submit();
        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Required')]")));
        assertEquals(alert.getText(), "Required");
    }

    @AfterClass
    public static void closeDriver() {
        driver.quit();
    }
}