package hw19;

import hw18.utils.Urls;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    static WebDriver driver;
    static WebDriverWait wait;
    static Actions actions;

    public RegistrationPage(WebDriver driver) {
        RegistrationPage.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "firstName")
    private static WebElement firstNameField;
    @FindBy(name = "lastName")
    private static WebElement lastNameField;
    @FindBy(name = "dateOfBirth")
    private static WebElement dateOfBirthField;
    @FindBy(name = "email")
    private static WebElement emailField;
    @FindBy(name = "password")
    private static WebElement passwordField;
    @FindBy(name = "passwordConfirmation")
    private static WebElement passwordConfirmationField;
    @FindBy(xpath = "//*[@type=\"submit\"]")
    private static WebElement submitButton;

    public RegistrationPage openRegistrationPage() {
        driver.get(Urls.REGISTRATION_PAGE.getLink());
        return this;
    }

    public RegistrationPage enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameField)).sendKeys(firstName);
        wait.until(ExpectedConditions.visibilityOf(lastNameField)).click();
        return this;
    }

    public RegistrationPage enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(lastNameField)).sendKeys(lastName);
        wait.until(ExpectedConditions.visibilityOf(dateOfBirthField)).click();
        return this;
    }

    public RegistrationPage enterDateOfBirth(String dateOfBirth) {
        wait.until(ExpectedConditions.visibilityOf(dateOfBirthField)).sendKeys(dateOfBirth);
        actions.moveToElement(lastNameField).click().build().perform();
        return this;
    }

    public RegistrationPage enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOf(passwordField)).click();
        return this;
    }

    public RegistrationPage enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOf(passwordConfirmationField)).click();
        return this;
    }

    public RegistrationPage confirmPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordConfirmationField)).sendKeys(password);
        return this;
    }

    public void submitRegistrationForm() {
        wait.until(ExpectedConditions.visibilityOf(submitButton)).click();
    }
}