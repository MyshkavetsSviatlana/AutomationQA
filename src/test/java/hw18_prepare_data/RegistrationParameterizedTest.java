package hw18_prepare_data;

import hw18.utils.Urls;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Driver;

import java.time.Duration;

public class RegistrationParameterizedTest {
    static WebDriver driver;
    static WebDriverWait wait;
    static Actions actions;

    @BeforeAll
    public static void varInit() {
        driver = Driver.setUpDriver();
        driver.get(Urls.REGISTRATION_PAGE.getLink());
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        actions = new Actions(driver);
    }

    private static class Locators {
        private static final By firstNameField = By.name("firstName");
        private static final By lastNameField = By.name("lastName");
        private static final By dateOfBirthField = By.name("dateOfBirth");
        private static final By emailField = By.name("email");
        private static final By passwordField = By.name("password");
        private static final By passwordConfirmationField = By.name("passwordConfirmation");
        private static final By submitButton = By.xpath("//*[@type=\"submit\"]");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/50_users_to_register.csv")
    public void registerUsersTest(String firstName,
                                         String lastName,
                                         String dataOfBirth,
                                         String email,
                                         String password) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.firstNameField)).sendKeys(firstName);
        WebElement lastNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.lastNameField));
        lastNameElement.sendKeys(lastName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.dateOfBirthField)).sendKeys(dataOfBirth);
        actions.moveToElement(lastNameElement).click().build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.emailField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.passwordField)).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.passwordConfirmationField)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(Locators.submitButton)).click();
        Thread.sleep(5000);
        if (driver.getCurrentUrl().equals(Urls.REGISTRATION_PAGE.getLink())) {
            driver.navigate().refresh();
        } else {driver.get(Urls.REGISTRATION_PAGE.getLink());}
    }

    @AfterAll
    public static void closeDriver() {
        driver.quit();
    }
}