package hw15.webelements_method;

/*
Write a method that accepts two WebElements as parameters.
The method will print to the console which of the two elements is positioned higher on the page,
which is positioned to the left, and which element takes up more space.
The method parameters may include additional arguments if necessary.
 */

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebElementsMethod {
    public static void compareWebElements(WebDriver driver, WebElement webElement1, WebElement webElement2) {
        // compare vertical position
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(webElement1, webElement2));
        if ((webElement1.getLocation().getY() - webElement2.getLocation().getY()) > 0) {
            System.out.println("WebElement2 is positioned higher on the page.");
        } else if ((webElement1.getLocation().getY() - webElement2.getLocation().getY()) < 0) {
            System.out.println("WebElement1 is positioned higher on the page.");
        } else {
            System.out.println("WebElements are positioned equally high on the page.");
        }
        // compare horizontal position
        if ((webElement1.getLocation().getX() - webElement2.getLocation().getX()) > 0) {
            System.out.println("WebElement2 is positioned to the left.");
        } else if ((webElement1.getLocation().getX() - webElement2.getLocation().getX()) < 0) {
            System.out.println("WebElement1 is positioned to the left.");
        } else {
            System.out.println("X-coordinates of the WebElements coincide.");
        }
        // compare space
        Dimension dimension1 = webElement1.getSize();
        double space1 = dimension1.height * dimension1.width;
        Dimension dimension2 = webElement2.getSize();
        double space2 = dimension2.height * dimension2.width;
        if ((space1 - space2) > 0) {
            System.out.println("WebElement1 takes up more space than webElement2.");
        } else if ((space1 - space2) < 0) {
            System.out.println("WebElement2 takes up more space than webElement1.");
        } else {
            System.out.println("WebElements are equal in space they take up on the page.");
        }
    }
}