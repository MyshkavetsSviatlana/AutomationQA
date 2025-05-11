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
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "firstName")
    private WebElement firstNameField;
    @FindBy(name = "lastName")
    private WebElement lastNameField;
    @FindBy(name = "dateOfBirth")
    private WebElement dateOfBirthField;
    @FindBy(name = "email")
    private WebElement emailField;
    @FindBy(name = "password")
    private WebElement passwordField;
    @FindBy(name = "passwordConfirmation")
    private WebElement passwordConfirmationField;
    @FindBy(xpath = "//*[@type=\"submit\"]")
    private WebElement submitButton;

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