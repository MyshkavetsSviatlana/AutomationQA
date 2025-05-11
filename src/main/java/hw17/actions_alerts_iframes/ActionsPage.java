package hw17.actions_alerts_iframes;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static Actions actions;

    public ActionsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "AlertButton")
    private WebElement confirmButton;

    @FindBy(xpath = "//button[contains(text(), 'Get Discount')]")
    private WebElement getDiscountButton;

    @FindBy(xpath = "//button[contains(text(), 'Cancel course')]")
    private WebElement cancelCourseButton;

    @FindBy(xpath = "//iframe[@title=\"Finish your registration\"]")
    private WebElement iframe;

    @FindBy(xpath = "//div[@class=\"flex p-6 bg-[#FAFAFA] text-[#000000]\"]")
    private WebElement resultMessage;


    public ActionsPage clickConfirmButton() {
        driver.switchTo().parentFrame();
        driver.switchTo().frame(iframe);
        wait.until(ExpectedConditions.visibilityOf(confirmButton)).click();
        return this;
    }

    public ActionsPage clickGetDiscountButton() {
        driver.switchTo().parentFrame();
        driver.switchTo().frame(iframe);
        actions.doubleClick(getDiscountButton).perform();
        return this;
    }

    public ActionsPage clickCancelCourseButton() {
        driver.switchTo().parentFrame();
        driver.switchTo().frame(iframe);
        actions.contextClick(cancelCourseButton).perform();
        return this;
    }

    public String getAlertText() {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public ActionsPage acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
        return this;
    }

    public ActionsPage sendKeysToAlert(String value) {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(value);
        return this;
    }

    public String getResultMessage () {
        return wait.until(ExpectedConditions.visibilityOf(resultMessage)).getText();
    }
}