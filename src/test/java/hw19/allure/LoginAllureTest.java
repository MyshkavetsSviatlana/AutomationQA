package hw19.allure;

import hw18.LoginPage;
import hw18.utils.Credentials;
import hw18.utils.Urls;
import io.qameta.allure.*;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.DriverWithBrowserOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LoginAllureTest {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static final Logger LOGGER = LogManager.getLogger();

    public static void takeScreenshot(WebDriver driver, String methodName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        try {
            String screenshotPath = "target/allure-results/screenshot-" + methodName + ".png";
            FileUtils.copyFile(source, new File(screenshotPath));

            Allure.addAttachment("Screenshot for " + methodName, new FileInputStream(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Failed for method '{}' ", methodName, e);
        }
    }

    @BeforeClass
    @Parameters({"browser"})
    public void varInit(String browser) {
        driver = DriverWithBrowserOptions.setUpDriver(browser);
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void makeScreenshotIfTestPass(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            takeScreenshot(driver, result.getMethod().getMethodName());
        }
    }

    @Description("Title check")
    @Severity(SeverityLevel.MINOR)
    @Story("2.1")
    @Test(priority = 1)
    public void titleTest() {
        loginPage.openLoginPage();
        LOGGER.info("Checking the title of the page...");
        LOGGER.info("The title is: {}.", driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "QA Course 01");
    }

    @Description("Email field placeholder check")
    @Severity(SeverityLevel.NORMAL)
    @Story("2.1")
    @Test(priority = 2)
    public static void emailPlaceholderTest() {
        LOGGER.info("Checking the email field placeholder...");
        LOGGER.info("Email field placeholder '{}' detected.", LoginPage.getEmailField().getDomAttribute("placeholder"));
        Assert.assertEquals(LoginPage.getEmailField().getDomAttribute("placeholder"), "Enter email");
    }

    @Description("Empty email field alert check")
    @Severity(SeverityLevel.NORMAL)
    @Story("2.1")
    @Test(priority = 3)
    public void emptyEmailTest() {
        loginPage.enterEmail("");
        LOGGER.info("Empty email field alert: {}.", loginPage.getRequiredText());
        Assert.assertEquals(loginPage.getRequiredText(), "Required");
    }

    @Description("Email field alert for leading spaces check")
    @Severity(SeverityLevel.CRITICAL)
    @Story("2.1")
    @Test(priority = 4)
    public void leadingSpacesTest() {
        driver.navigate().refresh();
        loginPage.enterEmail("   " + Credentials.REGISTERED_USER_LOGIN.getCredential());
        LOGGER.info("Email field alert for leading spaces: {}.", loginPage.getInvalidEmailText());
        Assert.assertEquals(loginPage.getInvalidEmailText(), "Invalid email address");
    }

    @Description("Email field alert for trailing spaces check")
    @Severity(SeverityLevel.CRITICAL)
    @Story("2.1")
    @Test(priority = 5)
    public void trailingSpacesTest() {
        driver.navigate().refresh();
        loginPage.enterEmail(Credentials.REGISTERED_USER_LOGIN.getCredential() + "   ");
        LOGGER.info("Email field alert for trailing spaces: {}.", loginPage.getInvalidEmailText());
        Assert.assertEquals(loginPage.getInvalidEmailText(), "Invalid email address");
    }

    @Description("Email field alert for mid spaces check")
    @Severity(SeverityLevel.CRITICAL)
    @Story("2.1")
    @Test(priority = 6)
    public void midSpacesTest() {
        driver.navigate().refresh();
        loginPage.enterEmail(Credentials.LOGIN_MID_SPACES.getCredential());
        LOGGER.info("Email field alert for mid spaces: {}.", loginPage.getInvalidEmailText());
        Assert.assertEquals(loginPage.getInvalidEmailText(), "Invalid email address");
    }

    @Description("Empty password field alert check")
    @Severity(SeverityLevel.NORMAL)
    @Story("2.1")
    @Test(priority = 8)
    public void emptyPasswordTest() {
        driver.navigate().refresh();
        loginPage.enterPassword("");
        LOGGER.info("Empty password field alert: {}.", loginPage.getRequiredText());
        Assert.assertEquals(loginPage.getRequiredText(), "Required");
    }

    @Description("Password field placeholder alert check")
    @Severity(SeverityLevel.NORMAL)
    @Story("2.1")
    @Test(priority = 9)
    public static void passwordPlaceholderTest() {
        driver.navigate().refresh();
        LOGGER.info("Checking the password field placeholder...");
        LOGGER.info("Password field placeholder '{}' detected.", LoginPage.getPasswordField().getDomAttribute("placeholder"));
        Assert.assertEquals(LoginPage.getPasswordField().getDomAttribute("placeholder"), "Enter password");
    }

    @Description("Registration link check")
    @Severity(SeverityLevel.CRITICAL)
    @Story("2.1")
    @Test(priority = 10)
    public void registrationLinkTest() {
        driver.navigate().refresh();
        loginPage.clickRegistrationLink();
        LOGGER.info("Checking the registration link...");
        Assert.assertEquals(driver.getCurrentUrl(), Urls.REGISTRATION_PAGE.getLink());
    }

    @AfterClass
    public static void closeDriver() {
        driver.quit();
    }
}