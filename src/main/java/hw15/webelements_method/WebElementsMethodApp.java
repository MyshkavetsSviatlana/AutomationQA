package hw15.webelements_method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import utils.Driver;
import utils.Links;

import static hw15.webelements_method.WebElementsMethod.compareWebElements;

public class WebElementsMethodApp {
    public static void main(String[] args) {
        WebDriver driver = Driver.setUpDriver();
        driver.manage().window().maximize();
        driver.get(Links.AUTOMATION_PRACTICE.getLink());
        WebElement popular = driver.findElement(By.xpath("//*[@class=\"homefeatured\"]"));
        WebElement contactUs = driver.findElement(By.xpath("//a[@title=\"Contact us\"]"));
        WebElement truckIcon = driver.findElement(By.xpath("//*[@class=\"icon-truck\"]"));
        WebElement phoneIcon = driver.findElement(By.xpath("//*[@id=\"icon-phone\"]"));

        compareWebElements(driver, popular, contactUs);
        compareWebElements(driver, truckIcon, phoneIcon);

        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get(Links.CLICK_SPEED_TESTER.getLink());
        WebElement clicksPerSec = driver.findElement(By.xpath("//*[@title=\"Clicks per second\"]"));
        WebElement clicks10Sec = driver.findElement(By.xpath("//a[contains(@href,'10-seconds')][@class=\"btn\"]"));

        compareWebElements(driver, clicksPerSec, clicks10Sec);
    }
}