package hw17.course_selection;

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

    @FindBy(xpath = "//*[@alt=\"Edit\"]")
    private static WebElement editButton;

    public LoginPage openLoginPage() {
        driver.get(Urls.LOGIN_PAGE.getLink());
        return this;
    }

    public void login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
        wait.until(ExpectedConditions.visibilityOf(editButton));
    }
}