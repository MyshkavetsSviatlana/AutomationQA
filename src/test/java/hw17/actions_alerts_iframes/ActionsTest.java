package hw17.actions_alerts_iframes;

/*
Automate the scenario shown in the video "Scenario for Automation Lecture 17 3.mp4"
to practice working with iFrames, Actions, and Alerts.

For each Alert, verify that its text matches the expected one.

You have called alert!
Are you sure you want to apply the discount?
Here you may describe a reason why you are cancelling your registration (or leave this field empty).
For the first and second Alerts, click the "OK" button and check the Result message to match
the following texts:

Congratulations, you have successfully enrolled in the course!
You received a 10% discount on the second course.
In the third Alert, enter the text "Test" in the input field and verify that after clicking
the "OK" button, the Result message contains the entered word.
 */

import hw17.course_selection.AccountPage;
import hw17.course_selection.Credentials;
import hw17.course_selection.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Driver;

public class ActionsTest {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static AccountPage accountPage;
    private static ActionsPage actionsPage;

    @BeforeClass
    public void setUp() {
        driver = Driver.setUpDriver();
        loginPage = new LoginPage(driver);
        loginPage.openLoginPage()
                .login(Credentials.REGISTERED_USER_LOGIN.getCredential(), Credentials.REGISTERED_USER_PASSWORD.getCredential());
        accountPage = new AccountPage(driver);
        accountPage.moveToPracticeButton()
                .chooseActionsAlertsIFramesOption();
        actionsPage = new ActionsPage(driver);
    }

    @Test
    public void confirmButtonTest() {
        actionsPage.clickConfirmButton();
        Assert.assertEquals(actionsPage.getAlertText(), "You have called alert!");
        actionsPage.acceptAlert();
        Assert.assertEquals(actionsPage.getResultMessage(), "Results:\n" + "Congratulations, you have successfully enrolled in the course!");

    }

    @Test
    public void getDiscountButtonTest() {
        actionsPage.clickGetDiscountButton();
        Assert.assertEquals(actionsPage.getAlertText(), "Are you sure you want to apply the discount?");
        actionsPage.acceptAlert();
        Assert.assertEquals(actionsPage.getResultMessage(), "Results:\n" + "You received a 10% discount on the second course.");
    }

    @Test
    public void cancelCourseButtonTest() {
        actionsPage.clickCancelCourseButton();
        Assert.assertEquals(actionsPage.getAlertText(), "Here you may describe a reason why you are cancelling your registration (or leave this field empty).");
        actionsPage.sendKeysToAlert("Test")
                .acceptAlert();
        Assert.assertTrue(actionsPage.getResultMessage().contains("Test"));
    }
}