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

public class RegistrationLinkSteps {
    WebDriver driver;
    WebDriverWait wait;
    static LoginPage loginPage;

    @Given("the driver is set up for registration link test")
    public void the_driver_is_set_up_for_registration_link_test() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
    }

    @When("the user is on login page for registration link test")
    public void the_user_is_on_login_for_registration_link_test() {
        driver.get(Urls.LOGIN_PAGE.getLink());
    }

    @And("registration link redirects to registration page")
    public void registration_link_redirects_to_registration_page() {
        driver.navigate().refresh();
        loginPage.clickRegistrationLink();
        Assert.assertEquals(driver.getCurrentUrl(), Urls.REGISTRATION_PAGE.getLink());
    }

    @Then("quit driver after registration link test")
    public void quit_driver_after_registration_link_test() {
        driver.quit();
    }
}