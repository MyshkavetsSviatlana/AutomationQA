package hw17.course_selection;

/*
Automate the scenario shown in the video "Scenario for Automation Lecture 17 1.mp4" to practice
working with the Select class.

Data:
➊ Country -> USA
➋ Language -> English
➌ Type -> Testing
➍ Start date -> Should be the next Monday, based on today's date.
➎ Last date -> Should be two weeks after the Start date.
➏ select courses -> Choose AQA Java and AQA Python.

Verify that the message "Unfortunately, we did not find any courses matching your chosen criteria." is displayed.
 */

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Driver;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class CourseSelectionTest {
    private static WebDriver driver;
    private static CourseSelectionPage courseSelectionPage;
    private static AccountPage accountPage;
    private static SearchResultPage searchResultPage;
    private static LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        driver = Driver.setUpDriver();
        loginPage = new LoginPage(driver);
        loginPage.openLoginPage()
                .login("john.doe@gmail.com", "Qwerty123");
        accountPage = new AccountPage(driver);
        accountPage.moveToPracticeButton()
                .chooseSelectOption();
        courseSelectionPage = new CourseSelectionPage(driver);
    }

    @BeforeClass
    public String calculateStartDate() {
        LocalDate dateNow = java.time.LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate nextMonday = dateNow.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        return nextMonday.format(formatter);
    }

    @BeforeClass
    public String calculateEndDate() {
        LocalDate dateNow = java.time.LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate nextMonday = dateNow.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        LocalDate courseEnd = nextMonday.plusDays(14);
        return courseEnd.format(formatter);
    }

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
        driver = Driver.getDriver();
        searchResultPage = new SearchResultPage(driver);
        Assert.assertEquals(searchResultPage.getSearchResult(), "Unfortunately, we did not find any courses matching your chosen criteria.");
    }
}