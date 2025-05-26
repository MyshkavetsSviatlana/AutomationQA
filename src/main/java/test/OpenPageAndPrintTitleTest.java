package test;

import io.cucumber.java.After;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import utils.Links;

import java.util.HashMap;
import java.util.Map;

public class OpenPageAndPrintTitleTest {
    WebDriver driver;
    String[] links = {"https://www.google.com/", "https://www.bbc.com/news", "https://edition.cnn.com/"};

    @Test
    public void openPageAndPrintTitle() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // loop through links and open them in new windows
        int counter = 0;
        Map<String, String> handles = new HashMap<>();
        for (String link : links) {
            driver.get(link);
            // print the title of each page to the console
            System.out.println("Current page title is: " + driver.getTitle());
            String handle = driver.getWindowHandle();
            handles.put(driver.getTitle(), handle);
            counter += 1;
            if (counter < Links.getSize()) {
                driver.switchTo().newWindow(WindowType.WINDOW);
            }
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}