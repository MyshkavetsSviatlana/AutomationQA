package hw19.allure;

import hw17.actions_alerts_iframes.ActionsPage;
import hw17.course_selection.AccountPage;
import hw17.course_selection.Credentials;
import hw17.course_selection.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.DriverWithBrowserOptions;

import static hw19.allure.LoginAllureTest.takeScreenshot;

public class ActionsAllureTest {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static AccountPage accountPage;
    private static ActionsPage actionsPage;
    private static final Logger LOGGER = LogManager.getLogger();

    @BeforeClass
    @Parameters({"browser"})
    public void varInit(String browser) {
        driver = DriverWithBrowserOptions.setUpDriver(browser);
        loginPage = new LoginPage(driver);
        loginPage.openLoginPage()
                .login(Credentials.REGISTERED_USER_LOGIN.getCredential(), Credentials.REGISTERED_USER_PASSWORD.getCredential());
        accountPage = new AccountPage(driver);
        accountPage.moveToPracticeButton()
                .chooseActionsAlertsIFramesOption();
        actionsPage = new ActionsPage(driver);
    }

    @Description("Confirm button check")
    @Severity(SeverityLevel.NORMAL)
    @Story("2.1")
    @Test
    public void confirmButtonTest() {
        actionsPage.clickConfirmButton();
        Assert.assertEquals(actionsPage.getAlertText(), "You have called alert!");
        actionsPage.acceptAlert();
        LOGGER.info("Confirm button alert has been accepted.");
        LOGGER.info("Enrollment: {}.", actionsPage.getResultMessage());
        Assert.assertEquals(actionsPage.getResultMessage(), "Results:\n" + "Congratulations, you have successfully enrolled in the course!");
    }

    @Description("Discount button check")
    @Severity(SeverityLevel.NORMAL)
    @Story("2.1")
    @Test
    public void getDiscountButtonTest() {
        actionsPage.clickGetDiscountButton();
        Assert.assertEquals(actionsPage.getAlertText(), "Are you sure you want to apply the discount?");
        actionsPage.acceptAlert();
        LOGGER.info("Get discount button alert has been accepted.");
        LOGGER.info("Discount: {}.", actionsPage.getResultMessage());
        Assert.assertEquals(actionsPage.getResultMessage(), "Results:\n" + "You received a 10% discount on the second course.");
    }

    @Description("Cancel course button check")
    @Severity(SeverityLevel.NORMAL)
    @Story("3.1")
    @Test
    public void cancelCourseButtonTest() {
        actionsPage.clickCancelCourseButton();
        Assert.assertEquals(actionsPage.getAlertText(), "Here you may describe a reason why you are cancelling your registration (or leave this field empty).");
        actionsPage.sendKeysToAlert("Test")
                .acceptAlert();
        LOGGER.info("Cancel course button alert has been accepted.");
        LOGGER.info("Cancellation message: {}.", actionsPage.getResultMessage());
        Assert.assertTrue(actionsPage.getResultMessage().contains("Test"));
    }

    @AfterMethod
    public void makeScreenshotIfTestPass(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            takeScreenshot(driver, result.getMethod().getMethodName());
        }
    }

    @AfterClass
    public static void closeDriver() {
        driver.quit();
    }
}