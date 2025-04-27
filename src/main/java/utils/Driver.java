package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Driver {
    private static WebDriver driver;

    //If you have a Driver class and initialize the driver for Chrome in this class,
    // then it's better to either call ChromeDriver directly or make the Driver class more flexible.
    public static WebDriver setUpDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
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

    private static WebDriver getInstance() {
        if (isDriverInvalid()) {
            driver = setUpDriver();
        }
        return driver;
    }

    public static WebDriver getDriver() {
        driver = getInstance();
        return driver;
    }
}
