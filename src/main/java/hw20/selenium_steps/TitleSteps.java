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

public class TitleSteps {
    WebDriver driver;
    WebDriverWait wait;
    static LoginPage loginPage;

    @Given("The driver is set up")
    public void the_driver_is_set() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
    }

    @When("the user is on login page")
    public void the_user_is_on_login_page() {
        driver.get(Urls.LOGIN_PAGE.getLink());
    }

    @And("the page title is \"QA Course 01\"")
    public void the_page_title_is_QA_Course_01() {
        Assert.assertEquals(driver.getTitle(), "QA Course 01");
    }

    @Then("quit driver")
    public void quit_driver() {
        driver.quit();
    }
}