package hw18;

import hw18.utils.Urls;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    static WebDriver driver;
    static WebDriverWait wait;
    static Actions actions;

    public LoginPage(WebDriver driver) {
        LoginPage.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "email")
    private static WebElement emailField;

    @FindBy(name = "password")
    private static WebElement passwordField;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    private static WebElement signInButton;

    @FindBy(xpath = "//*[@ class=\"mt-3 block cursor-pointer hover:underline\"]")
    private static WebElement registrationLink;

    @FindBy(xpath = "//*[@alt=\"Edit\"]")
    private static WebElement editButton;

    @FindBy(xpath = "//span[contains(text(), 'Invalid email')]")
    private static WebElement invalidEmail;

    @FindBy(xpath = "//span[contains(text(), 'Required')]")
    private static WebElement required;

    public LoginPage openLoginPage() {
        driver.get(Urls.LOGIN_PAGE.getLink());
        return this;
    }

    public LoginPage enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOf(passwordField)).click();
        return this;
    }

    public LoginPage enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
        return this;
    }

    public void clickRegistrationLink() {
        wait.until(ExpectedConditions.visibilityOf(registrationLink)).click();
    }

    public void login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
        wait.until(ExpectedConditions.visibilityOf(editButton));
    }

    public String getInvalidEmailText() {
        wait.until(ExpectedConditions.visibilityOf(invalidEmail));
        return invalidEmail.getText();
    }

    public String getRequiredText() {
        wait.until(ExpectedConditions.visibilityOf(required));
        return required.getText();
    }

    public static WebElement getEmailField() {
        return emailField;
    }

    public static WebElement getPasswordField() {
        return passwordField;
    }
}