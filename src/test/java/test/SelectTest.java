package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class SelectTest {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    @Test
    public void selectTextMethod() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.automationpractice.pl/index.php");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By dressesRelativeLocator = RelativeLocator.with(By.xpath("//a[@title=\"Dresses\"]")).below(By.xpath("//img[@alt=\"My Shop\"]"));
        //By dressesField = By.xpath("//div[@id=\"block_top_menu\"]/ul/li/a[contains(text(), 'Dresses')]");
        //By eveningDressesField = By.xpath("//ul[@class=\"submenu-container clearfix first-in-line-xs\"]/li/a[@title=\"Evening Dresses\"]");
        By eveningDressesRelativeLocator = RelativeLocator.with(By.xpath("//a[@title=\"Evening Dresses\"]")).below(By.xpath("//img[@alt=\"My Shop\"]"));
        Actions action = new Actions(driver);
        //action.moveToElement(wait.until(ExpectedConditions.visibilityOfElementLocated(dressesField))).perform();
        action.moveToElement(wait.until(ExpectedConditions.visibilityOfElementLocated(dressesRelativeLocator))).perform();
        //WebElement eveningDress = wait.until(ExpectedConditions.visibilityOfElementLocated(eveningDressesField));
        WebElement eveningDress = wait.until(ExpectedConditions.visibilityOfElementLocated(eveningDressesRelativeLocator));
        eveningDress.click();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}