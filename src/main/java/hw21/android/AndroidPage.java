package hw21.android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.LinkedHashSet;
import java.util.List;

public class AndroidPage {
    AppiumDriver driver;
    WebDriverWait wait;

    public AndroidPage(AppiumDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private static final class Locators {
        private static final By views = AppiumBy.accessibilityId("Views");
        private static final By elements = By.className("android.widget.TextView");
        private static final By dataWidgets = AppiumBy.accessibilityId("Date Widgets");
        private static final By dialog = AppiumBy.accessibilityId("1. Dialog");
        private static final By datePicker = AppiumBy.accessibilityId("change the date");
        private static final By date = AppiumBy.accessibilityId("20 May 2025");
        private static final By okButton = AppiumBy.id("android:id/button1");
        private static final By timeSpinner = AppiumBy.accessibilityId("change the time (spinner)");
        private static final By hourSpinner = AppiumBy.xpath("//android.widget.EditText[@bounds=\"[246,1101][414,1227]\"]");
        private static final By minuteSpinner = AppiumBy.xpath("//android.widget.EditText[@bounds=\"[455,1101][623,1227]\"]");
        private static final By pm = AppiumBy.xpath("//*[@text=\"PM\"]");
        private static final By dateTime = AppiumBy.xpath("//android.widget.TextView[@resource-id=\"io.appium.android.apis:id/dateDisplay\"]");
    }

    public String getDateTime() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.dateTime)).getText();
    }

    public void clickOnView() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.views)).click();
    }

    public void clickOnDataWidgets() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.dataWidgets)).click();
    }

    public void clickOnDatePicker() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.datePicker)).click();
    }

    public void clickOnDialog() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.dialog)).click();
    }

    public void chooseDate() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.date)).click();
    }

    public void clickOnTimeSpinner() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.timeSpinner)).click();
    }

    public void setTime(String hour, String minute) {
        WebElement minutes = wait.until(ExpectedConditions.elementToBeClickable(Locators.minuteSpinner));
        while(!minutes.getDomAttribute("text").equals(minute)) {
            this.scrollDownW3C(600, 1101, 600, 1227);}
        WebElement hours = wait.until(ExpectedConditions.elementToBeClickable(Locators.hourSpinner));
        while(!hours.getDomAttribute("text").equals(hour)) {
            this.scrollDownW3C(246, 1101, 246, 1227);}
    }

    // Method to scroll down using W3C Actions
    public void scrollDownW3C(int startX , int startY, int endX, int endY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(List.of(swipe));
    }

    public void setPm() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.pm)).click();
    }

    public void clickOnOkButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.okButton)).click();
    }

    public int totalNumberOfClickableOnView() {
        LinkedHashSet<String> elements = this.NumberOfVisibleClickableElements();
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"ImageView\"))"));
        elements.addAll(this.NumberOfVisibleClickableElements());
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"Rotating Button\"))"));
        elements.addAll(this.NumberOfVisibleClickableElements());
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"WebView3\"))"));
        elements.addAll(this.NumberOfVisibleClickableElements());
        System.out.println(elements);
        return elements.size();
    }

    public LinkedHashSet<String> NumberOfVisibleClickableElements() {
        List<WebElement> allElements = driver.findElements(Locators.elements);
        LinkedHashSet<String> clickableElements = new LinkedHashSet<>();
        for (WebElement element : allElements) {
            if (element.getDomAttribute("clickable").equals("true")) {
                clickableElements.add(element.getDomAttribute("text"));
            }
        }
        return clickableElements;
    }
}