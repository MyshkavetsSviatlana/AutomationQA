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
import org.testng.annotations.Test;

import java.time.Duration;

public class TitleAllureSteps {
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

    @Given("the user is on login page")
    public void the_user_is_on_login_page() {
        System.out.println(driver.getCurrentUrl());
        driver.get(Urls.LOGIN_PAGE.getLink());
    }

    @Then("the page title is \"QA Course 01\"")
    @Test(priority = 3)
    public void the_page_title_is_QA_Course_01() {
        Assert.assertEquals(driver.getTitle(), "QA Course 01");
    }

    @After
    public void quit_driver() {
        driver.quit();
    }
}