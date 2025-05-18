package hw21.android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
    }

    public void clickOnView() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.views)).click();
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