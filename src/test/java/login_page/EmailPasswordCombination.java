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

public class EmailPasswordCombination {
    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeClass
    public static void varInit() {
        driver = Driver.setUpDriver();
        driver.get(Urls.LOGIN_PAGE.getLink());
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public static void alertUnregisteredUser() {
        driver.navigate().refresh();
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        email.sendKeys("doe.john@gmail.com");
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        password.sendKeys("Qwerty123");
        WebElement signInButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type=\"submit\"]")));
        signInButton.click();
        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'is not valid')]")));
        Assert.assertEquals(alert.getText(), "Email or password is not valid");
    }

    @Test
    public static void alertInvalidPassword() {
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        email.sendKeys("john.doe@gmail.com");
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        password.sendKeys("Qwerty1234");
        WebElement signInButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type=\"submit\"]")));
        signInButton.click();
        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'is not valid')]")));
        Assert.assertEquals(alert.getText(), "Email or password is not valid");
    }

    @AfterClass
    public static void closeDriver() {
        driver.quit();
    }
}