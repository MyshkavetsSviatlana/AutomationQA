package hw18;

import hw18.utils.Urls;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login50UsersTest {
    private static WebDriver driver;
    private static LoginPage loginPage;

    @BeforeEach
    public void varInit() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(Urls.LOGIN_PAGE.getLink());
        loginPage = new LoginPage(driver);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/50_registered_users.csv")
    public void login50UsersTest(String email, String password) {
        loginPage.login(email, password);
        driver.get(Urls.LOGIN_PAGE.getLink());
    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }
}