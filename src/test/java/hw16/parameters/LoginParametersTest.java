package hw16.parameters;

/*
Pre-create three users for our website. Проверить логин этих трех пользователей.
Solve the previous task using the @Parameters annotation. Also, create an additional XML file testngParametersHome.xml
to work with this test.
 */

import hw15.utils.Urls;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.Driver;

import java.time.Duration;

public class LoginParametersTest {
    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeClass
    public static void varInit() {
        driver = Driver.setUpDriver();
        driver.get(Urls.LOGIN_PAGE.getLink());
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private static class Locators {
        private static final By emailField = By.name("email");
        private static final By passwordField = By.name("password");
        private static final By signInButtonField = By.xpath("//button[@type=\"submit\"]");
        private static final By editButton = By.xpath("//*[@alt=\"Edit\"]");
    }

    @Test
    @Parameters({"email", "password"})
    public static void paramLoginTest(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.emailField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.passwordField)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(Locators.signInButtonField)).click();
        WebElement avatar = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.editButton));
        Assert.assertTrue(avatar.isDisplayed());
        driver.get(Urls.LOGIN_PAGE.getLink());
    }

    @AfterClass
    public static void closeDriver() {
        driver.quit();
    }
}