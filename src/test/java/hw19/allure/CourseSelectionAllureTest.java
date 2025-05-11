package hw19.allure;

import hw17.course_selection.*;
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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import static hw19.allure.LoginAllureTest.takeScreenshot;

public class CourseSelectionAllureTest {
    private WebDriver driver;
    private static CourseSelectionPage courseSelectionPage;
    private static AccountPage accountPage;
    private static SearchResultPage searchResultPage;
    private static LoginPage loginPage;
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
                .chooseSelectOption();
        courseSelectionPage = new CourseSelectionPage(driver);
    }

    @BeforeClass
    public String calculateStartDate() {
        LocalDate dateNow = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate nextMonday = dateNow.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        return nextMonday.format(formatter);
    }

    @BeforeClass
    public String calculateEndDate() {
        LocalDate dateNow = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate nextMonday = dateNow.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        LocalDate courseEnd = nextMonday.plusDays(14);
        return courseEnd.format(formatter);
    }

    @Description("Course selection check")
    @Severity(SeverityLevel.CRITICAL)
    @Story("3.2")
    @Test
    public void courseSelectionTest() {
        courseSelectionPage.selectCountry("USA")
                .selectLanguage("English")
                .selectType("Testing")
                .selectDateFrom(calculateStartDate())
                .selectDateTo(calculateEndDate())
                .selectCourse("AQA Java")
                .selectCourse("AQA Python")
                .submitForm();
        searchResultPage = new SearchResultPage(driver);
        LOGGER.info("Search result text: {}", searchResultPage.getSearchResult());
        Assert.assertEquals(searchResultPage.getSearchResult(), "Unfortunately, we did not find any courses matching your chosen criteria.");
    }

    @AfterMethod
    public void makeScreenshotIfTestPass(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            takeScreenshot(driver, result.getMethod().getMethodName());
        }
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }
}