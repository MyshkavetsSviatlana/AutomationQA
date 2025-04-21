package hw15.change_avatar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Driver;

import java.time.Duration;

public class ChangeAvatar {
    public static void main(String[] args) {
        // open log in page
        WebDriver driver = Driver.setUpDriver();
        driver.manage().window().maximize();
        driver.get("https://qa-course-01.andersenlab.com/login");
        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement signIn = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        //log in user
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(email, password, signIn));
        email.sendKeys("john.doe@gmail.com");
        password.sendKeys("Qwerty123");
        signIn.click();
        //change avatar
        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type=file]")));
        fileInput.sendKeys("/Users/sviatlanamyshkavets/Documents/Courses/AQA Java/Lecture 15.6. pooh.png");
        //print the message from the WebElement
        WebElement message = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class=\"mt-12\"]")));
        System.out.println(message.getText());
    }
}