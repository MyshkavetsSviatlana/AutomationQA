package hw20.selenium_steps;

import hw18.LoginPage;
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

public class EmailFieldPlaceholderSteps {
    WebDriver driver;
    WebDriverWait wait;
    static LoginPage loginPage;

    @Given("The driver is set up for email placeholder test")
    public void the_driver_is_set_up_for_email_placeholder_test() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
    }

    @When("the user is on login page for email placeholder test")
    public void the_user_is_on_login_page_for_email_placeholder_test() {
        driver.get(Urls.LOGIN_PAGE.getLink());
    }

    @And("email field placeholder should be \"Enter email\"")
    public void email_field_placeholder_should_be_Enter_email() {
        Assert.assertEquals(loginPage.getEmailField().getDomAttribute("placeholder"), "Enter email");
    }

    @Then("quit driver after email placeholder test")
    public void quit_driver_after_email_placeholder_test() {
        driver.quit();
    }
}