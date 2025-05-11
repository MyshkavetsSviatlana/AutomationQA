package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.List;
import java.util.Set;

public class DriverWithBrowserOptions implements WebDriver {
    private static WebDriver driver;

    public static WebDriver setUpDriver(String browser) {
        switch (browser) {
            case "Safari" -> {
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
            }
            case "Chrome" -> {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            case "Firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
        }
        driver.manage().window().maximize();
        return driver;
    }

    private static boolean isDriverInvalid() {
        try {
            return driver == null || ((RemoteWebDriver) driver).getSessionId() == null;
        } catch (Exception e) {
            return true;
        }
    }

    private static WebDriver getInstance(String browser) {
        if (isDriverInvalid()) {
            driver = setUpDriver(browser);
        }
        return driver;
    }

    public static WebDriver getDriver(String browser) {
        driver = getInstance(browser);
        return driver;
    }

    @Override
    public void get(String url) {

    }

    @Override
    public String getCurrentUrl() {
        return "";
    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public List<WebElement> findElements(By by) {
        return List.of();
    }

    @Override
    public WebElement findElement(By by) {
        return null;
    }

    @Override
    public String getPageSource() {
        return "";
    }

    @Override
    public void close() {

    }

    @Override
    public void quit() {

    }

    @Override
    public Set<String> getWindowHandles() {
        return Set.of();
    }

    @Override
    public String getWindowHandle() {
        return "";
    }

    @Override
    public TargetLocator switchTo() {
        return null;
    }

    @Override
    public Navigation navigate() {
        return null;
    }

    @Override
    public Options manage() {
        return null;
    }
}
