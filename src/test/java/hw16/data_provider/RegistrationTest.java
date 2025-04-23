package hw16.data_provider;

import hw15.utils.Urls;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Driver;

import java.time.Duration;

public class RegistrationTest {
    static WebDriver driver;
    static WebDriverWait wait;
    static Actions actions;

    @BeforeClass
    public static void varInit() {
        driver = Driver.setUpDriver();
        driver.get(Urls.REGISTRATION_PAGE.getLink());
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }

    @DataProvider(name = "testData")
    public Object[][] createData() {
        return new Object[][]{
                {"FN1", "LN1", "09.09.2009", "mail1@mail1.mail", "TestTest1"},
                {"FN2", "LN2", "10.10.2010", "mail2@mail2.mail", "TestTest2"},
                {"FN3", "LN3", "03.03.2003", "mail3@mail3.mail", "TestTest3"},
        };
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

    @Test(dataProvider = "testData")
    public static void registerUsersTest(String firstName, String lastName, String dataOfBirth, String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.firstNameField)).sendKeys(firstName);
        WebElement lastNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.lastNameField));
        lastNameElement.sendKeys(lastName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.dateOfBirthField)).sendKeys(dataOfBirth);
        actions.moveToElement(lastNameElement).click().build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.emailField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.passwordField)).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.passwordConfirmationField)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(Locators.submitButton)).click();
        driver.get(Urls.REGISTRATION_PAGE.getLink());
    }

    @AfterClass
    public static void closeDriver() {
        driver.quit();
    }
}