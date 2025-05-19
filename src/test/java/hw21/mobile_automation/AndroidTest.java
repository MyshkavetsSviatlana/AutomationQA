package hw21.mobile_automation;

import hw21.android.AndroidPage;
import hw21.android.AppiumDriverInit;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AndroidTest {
    AppiumDriver driver;
    AndroidPage androidPage;

    @BeforeMethod
    public void setUp() {
        driver = new AppiumDriverInit().getDriver();
        androidPage = new AndroidPage(driver);
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }

    @Test
    public void checkNumberOfElements() {
        androidPage.clickOnView();
        Assert.assertEquals(androidPage.totalNumberOfClickableOnView(), 42);
    }

    @Test
    public void changeDateAndTime() {
        androidPage.clickOnView();
        androidPage.clickOnDataWidgets();
        androidPage.clickOnDialog();
        androidPage.clickOnDatePicker();
        androidPage.chooseDate();
        androidPage.clickOnOkButton();
        androidPage.clickOnTimeSpinner();
        androidPage.setTime("11", "11");
        androidPage.setPm();
        androidPage.clickOnOkButton();
        Assert.assertEquals(androidPage.getDateTime(), "5-20-2025 23:11");
    }
}