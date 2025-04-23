package hw16.parameters;

import hw15.utils.Urls;
import utils.Driver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginParameterizedTest {
    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeAll
    public static void varInit() {
        driver = Driver.setUpDriver();
        driver.get(Urls.LOGIN_PAGE.getLink());
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println(driver.getCurrentUrl());
    }

    private static class Locators {
        private static final By emailField = By.name("email");
        private static final By passwordField = By.name("password");
        private static final By signInButtonField = By.xpath("//button[@type=\"submit\"]");
        private static final By editButton = By.xpath("//*[@alt=\"Edit\"]");
    }

    @ParameterizedTest
    @CsvSource({"mail1@mail.mail,TestTest1", "mail2@mail.mail,TestTest2", "mail3@mail.mail,TestTest3"})
    public void loginTest(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.emailField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.passwordField)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(Locators.signInButtonField)).click();
        WebElement avatar = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.editButton));
        assertTrue(avatar.isDisplayed());
        driver.get(Urls.LOGIN_PAGE.getLink());
    }

    @AfterAll
    public static void closeDriver() {
        driver.quit();
    }
}