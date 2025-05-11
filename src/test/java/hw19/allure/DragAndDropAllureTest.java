package hw19.allure;

import hw17.course_selection.AccountPage;
import hw17.course_selection.Credentials;
import hw17.course_selection.LoginPage;
import hw17.drag_and_drop.DragAndDropPage;
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

public class DragAndDropAllureTest {
    private static WebDriver driver;
    private static AccountPage accountPage;
    private static LoginPage loginPage;
    private static DragAndDropPage dragAndDropPage;
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
                .chooseDragAndDropOption();
        dragAndDropPage = new DragAndDropPage(driver);
    }

    @Description("Drag And Drop check")
    @Severity(SeverityLevel.CRITICAL)
    @Story("3.3")
    @Test
    public void dragAndDropTest() {
        dragAndDropPage.dragWriteTestCases()
                .dragTestingRequirements()
                .dragWriteAutomationScripts()
                .dragFrameworkSetUp();
        LOGGER.info("Success message is: {}", dragAndDropPage.getSuccessMessageText());
        Assert.assertEquals(dragAndDropPage.getSuccessMessageText(), "Congratulations! Let's test for the best!");
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