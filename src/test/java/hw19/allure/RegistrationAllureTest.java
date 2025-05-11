package hw19.allure;

import hw18.utils.Urls;
import hw19.RegistrationPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.DriverWithBrowserOptions;

public class RegistrationAllureTest {
    private WebDriver driver;
    private static RegistrationPage registrationPage;
    private static final Logger LOGGER = LogManager.getLogger();

    @BeforeClass
    @Parameters({"browser"})
    public void varInit(String browser) {
        driver = DriverWithBrowserOptions.setUpDriver(browser);
        driver.get(Urls.REGISTRATION_PAGE.getLink());
        registrationPage = new RegistrationPage(driver);
    }

    @DataProvider(name = "testData")
    public Object[][] createData() {
        return new Object[][]{
                {"F1", "L1", "01.01.2001", "mail1@gmail1.mail", "TestTest1"},
                {"F2", "L2", "01.01.2001", "mail2@gmail2.mail", "TestTest2"},
                {"F3", "L3", "01.01.2001", "mail3@gmail3.mail", "TestTest3"},
                {"F4", "L4", "01.01.2001", "mail4@gmail4.mail", "TestTest4"},
                {"F5", "L5", "01.01.2001", "mail5@gmail5.mail", "TestTest5"},
                {"F6", "L6", "01.01.2001", "mail6@gmail6.mail", "TestTest6"},
                {"F7", "L7", "01.01.2001", "mail7@gmail7.mail", "TestTest7"},
                {"F8", "L8", "01.01.2001", "mail8@gmail8.mail", "TestTest8"},
                {"F9", "L9", "01.01.2001", "mail9@gmail9.mail", "TestTest9"},
                {"F10", "L10", "01.01.2001", "mail10@gmail10.mail", "TestTest10"},
        };
    }

    @Description("Register multiple users check")
    @Severity(SeverityLevel.CRITICAL)
    @Story("1")
    @Test(dataProvider = "testData")
    public void registerMultipleUsersTest(String firstName,
                                          String lastName,
                                          String dataOfBirth,
                                          String email,
                                          String password) throws InterruptedException {
        registrationPage.enterFirstName(firstName)
                .enterLastName(lastName)
                .enterDateOfBirth(dataOfBirth)
                .enterEmail(email)
                .enterPassword(password)
                .confirmPassword(password)
                .submitRegistrationForm();
        LOGGER.debug("Registering user with email '{}'.", email);
        Thread.sleep(3000);
        if (driver.getCurrentUrl().equals(Urls.REGISTRATION_PAGE.getLink())) {
            LOGGER.warn("The user with email '{}' already exists.", email);
            driver.navigate().refresh();
        } else {
            LOGGER.info("The user was successfully registered.");
            driver.get(Urls.REGISTRATION_PAGE.getLink());
        }
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }
}