package hw15.open_page_print_title;

/*
Write a program that will open five different pages in new windows:
http://www.automationpractice.pl/index.php, https://zoo.waw.pl/, https://www.w3schools.com/
https://www.clickspeedtester.com/click-counter/, https://andersenlab.com/
Write a loop that will sequentially switch between all the pages, printing the title and link of each page to the console.
Close the page that contains the word "Zoo" in the title.
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import utils.Driver;
import utils.Links;
import java.util.HashMap;
import java.util.Map;

public class OpenPageAndPrintTitle {
    public static void main(String[] args) {
        WebDriver driver = Driver.setUpDriver();
        driver.manage().window().maximize();
        // loop through links and open them in new windows
        int counter = 0;
        Map<String, String> handles = new HashMap<>();
        for (Links link : Links.values()) {
            driver.get(link.getLink());
            // print the title and link of each page to the console
            System.out.println("Current page title is: " + driver.getTitle());
            System.out.println("Current link is: " + link.getLink());
            String handle = driver.getWindowHandle();
            handles.put(driver.getTitle(), handle);
            counter += 1;
            if (counter < Links.getSize()) {
                driver.switchTo().newWindow(WindowType.WINDOW);
            }
        }
        // close the page that contains the word "Zoo" in the title.
        for (String i : handles.keySet()) {
            if (i.contains("Zoo")) {
                driver.switchTo().window(handles.get(i)).close();
            }
        }
    }
}