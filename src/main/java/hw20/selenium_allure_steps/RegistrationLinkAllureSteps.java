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

public class RegistrationLinkAllureSteps {
    WebDriver driver;
    WebDriverWait wait;
    static LoginPage loginPage;

    @Before
    public void set_up_driver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
    }

    @Given("the user is on login page for registration link test")
    public void the_user_is_on_login_for_registration_link_test() {
        driver.get(Urls.LOGIN_PAGE.getLink());
    }

    @Then("click on registration link redirects to registration page")
    public void click_on_registration_link_redirects_to_registration_page() {
        driver.navigate().refresh();
        loginPage.clickRegistrationLink();
        Assert.assertEquals(driver.getCurrentUrl(), Urls.REGISTRATION_PAGE.getLink());
    }

    @After
    public void quit_driver() {
        driver.quit();
    }
}