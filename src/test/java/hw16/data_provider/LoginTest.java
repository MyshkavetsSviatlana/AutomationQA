package hw16.data_provider;

/*
Pre-create three users for our website. Написать тест используя @DataProvider, который будет проверять
логин этих трех пользователей.
 */

import hw15.utils.Urls;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Driver;

import java.time.Duration;

public class LoginTest {
    static WebDriver driver;
    static WebDriverWait wait;
    static Actions actions;

    @BeforeClass
    public static void varInit() {
        driver = Driver.setUpDriver();
        driver.get(Urls.LOGIN_PAGE.getLink());
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }

    @DataProvider(name = "testData")
    public Object[][] createData() {
        return new Object[][]{
                {"mail1@mail.mail", "TestTest1"},
                {"mail2@mail.mail", "TestTest2"},
                {"mail3@mail.mail", "TestTest3"},
        };
    }

    private static class Locators {
        private static final By emailField = By.name("email");
        private static final By passwordField = By.name("password");
        private static final By signInButtonField = By.xpath("//button[@type=\"submit\"]");
        private static final By editButton = By.xpath("//*[@alt=\"Edit\"]");
    }

    @Test(dataProvider = "testData")
    public static void loginTest(String email, String password) {
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