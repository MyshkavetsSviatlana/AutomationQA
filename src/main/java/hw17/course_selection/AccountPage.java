package hw17.course_selection;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static Actions actions;

    @FindBy(xpath = "//div[contains(text(), 'AQA Practice')]")
    private static WebElement practice;
    @FindBy(xpath = "//div[contains(text(), 'Select')]")
    private static WebElement select;
    @FindBy(xpath = "//div[contains(text(), 'Drag & Drop')]")
    private static WebElement dragAndDrop;
    @FindBy(xpath = "//div[contains(text(), 'Alerts')]")
    private static WebElement actionsAlertsIFrames;

    public AccountPage(WebDriver driver) {
        AccountPage.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public AccountPage moveToPracticeButton() {
        wait.until(ExpectedConditions.visibilityOf(practice)).click();
        return this;
    }

    public void chooseSelectOption() {
        wait.until(ExpectedConditions.visibilityOf(select)).click();
    }

    public void chooseDragAndDropOption() {
        actions.moveToElement(dragAndDrop).click().build().perform();
    }

    public void chooseActionsAlertsIFramesOption() {
        actions.moveToElement(actionsAlertsIFrames).click().build().perform();
    }
}