package hw20.selenium_allure_steps;

import hw18.LoginPage;
import hw18.utils.Urls;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class EmailFieldPlaceholderAllureSteps {
    WebDriver driver;
    WebDriverWait wait;
    static LoginPage loginPage;

    @Before
    public void the_driver_is_set_up_for_email_placeholder_test() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
    }

    @Given("the user is on login page for email placeholder test")
    public void the_user_is_on_login_page_for_email_placeholder_test() {
        driver.get(Urls.LOGIN_PAGE.getLink());
    }

    @Then("email field placeholder should be \"Enter email\"")
    public void email_field_placeholder_should_be_Enter_email() {
        Assert.assertEquals(loginPage.getEmailField().getDomAttribute("placeholder"), "Enter email");
    }

    @After
    public void quit_driver() {
        driver.quit();
    }
}