package hw20.selenium_steps;

import hw18.LoginPage;
import hw18.utils.Credentials;
import hw18.utils.Urls;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LeadingSpacesEmailSteps {
    WebDriver driver;
    WebDriverWait wait;
    static LoginPage loginPage;

    @Given("The driver is set up for email with leading spaces test")
    public void the_driver_is_set_up_for__for_email_placeholder_test() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
    }

    @When("the user is on login page for email with leading spaces test")
    public void the_user_is_on_login_page__for_empty_email_test() {
        driver.get(Urls.LOGIN_PAGE.getLink());
    }

    @And("the leading spaces should cause \"Required\" alert")
    public void the_leading_spaces_should_cause_Required_alert() {
        driver.navigate().refresh();
        loginPage.enterEmail("   " + Credentials.REGISTERED_USER_LOGIN.getCredential());
        Assert.assertEquals(loginPage.getInvalidEmailText(), "Invalid email address");
    }

    @Then("quit driver after email with leading spaces test")
    public void quit_driver_after_email_with_leading_spaces_test() {
        driver.quit();
    }
}