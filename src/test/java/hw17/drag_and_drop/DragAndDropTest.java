package hw17.drag_and_drop;

/*
Automate the scenario shown in the video "Scenario for Automation Lecture 17 2.mp4"
to practice automating Drag and Drop functionality.
Verify that the message "Congratulations! Let's test for the best!" is displayed.
 */

import hw17.course_selection.AccountPage;
import hw17.course_selection.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Driver;

public class DragAndDropTest {
    private static WebDriver driver;
    private static AccountPage accountPage;
    private static LoginPage loginPage;
    private static DragAndDropPage dragAndDropPage;

    @BeforeClass
    public void setUp() {
        driver = Driver.setUpDriver();
        loginPage = new LoginPage(driver);
        loginPage.openLoginPage()
                .login("john.doe@gmail.com", "Qwerty123");
        accountPage = new AccountPage(driver);
        accountPage.moveToPracticeButton()
                .chooseDragAndDropOption();
        dragAndDropPage = new DragAndDropPage(driver);
    }

    @Test
    public void dragAndDropTest() {
        dragAndDropPage.dragWriteTestCases()
                .dragTestingRequirements()
                .dragWriteAutomationScripts()
                .dragFrameworkSetUp();
        Assert.assertEquals(dragAndDropPage.getSuccessMessageText(), "Congratulations! Let's test for the best!");
    }
}