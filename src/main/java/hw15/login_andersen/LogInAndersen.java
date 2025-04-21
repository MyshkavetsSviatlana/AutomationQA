package hw15.login_andersen;

/*
Write a script that opens a browser and logs in an already registered user on the QA Course 01 (andersenlab.com).
https://qa-course-01.andersenlab.com/login
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Driver;

public class LogInAndersen {
    public static void main(String[] args) {
        WebDriver driver = Driver.setUpDriver();
        driver.get("https://qa-course-01.andersenlab.com/login");

        driver.findElement(By.name("email")).sendKeys("john.doe@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Qwerty123");
        driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
    }
}