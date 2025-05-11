package hw19.allure;

import hw18.LoginPage;
import hw18.utils.Urls;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.DriverWithBrowserOptions;

public class LoginMultipleUsersAllureTest {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static final Logger LOGGER = LogManager.getLogger();

    @BeforeClass
    @Parameters("browser")
    public void varInit(String browser) {
        driver = DriverWithBrowserOptions.setUpDriver(browser);
        driver.get(Urls.LOGIN_PAGE.getLink());
        loginPage = new LoginPage(driver);
    }

    @DataProvider(name = "testData")
    public Object[][] createData() {
        return new Object[][]{
                {"mail1@gmail1.mail", "TestTest1"},
                {"mail2@gmail2.mail", "TestTest2"},
                {"mail3@gmail3.mail", "TestTest3"},
                {"mail4@gmail4.mail", "TestTest4"},
                {"mail5@gmail5.mail", "TestTest5"},
                {"mail6@gmail6.mail", "TestTest6"},
                {"mail7@gmail7.mail", "TestTest7"},
                {"mail8@gmail8.mail", "TestTest8"},
                {"mail9@gmail9.mail", "TestTest9"},
                {"mail10@gmail10.mail", "TestTest10"},
        };
    }

    @Description("Login multiple users check")
    @Severity(SeverityLevel.CRITICAL)
    @Story("2.2")
    @Test(dataProvider = "testData")
    public void loginMultipleUsersTest(String email, String password) {
        loginPage.login(email, password);
        LOGGER.info("Logging in user with email {}...", email);
        driver.get(Urls.LOGIN_PAGE.getLink());
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }
}