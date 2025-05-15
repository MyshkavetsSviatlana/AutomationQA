package hw20.selenium_allure_steps;

import hw18.LoginPage;
import hw18.utils.Urls;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class EmptyEmailAllureSteps {
    WebDriver driver;
    WebDriverWait wait;
    static LoginPage loginPage;

    @Before
    public void The_driver_is_set_up_for_empty_email_test() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
    }

    @When("the user is on login page for empty email test")
    public void the_user_is_on_login_page_for_empty_email_test() {
        driver.get(Urls.LOGIN_PAGE.getLink());
    }

    @Then("the email field alert should display \"Required\"")
    public void the_email_field_alert_should_display_Required() {
        loginPage.enterEmail("");
        Assert.assertEquals(loginPage.getRequiredText(), "Required");
    }

    @After
    public void quit_driver() {
        driver.quit();
    }
}