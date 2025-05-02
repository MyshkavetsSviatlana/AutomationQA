package hw17.drag_and_drop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DragAndDropPage {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static Actions actions;

    public DragAndDropPage(WebDriver driver) {
        DragAndDropPage.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[contains(text(), 'Write test cases')]")
    private static WebElement writeTestCases;

    @FindBy(xpath = "//span[contains(text(), 'Testing requirements')]")
    private static WebElement testingRequirements;

    @FindBy(xpath = "//span[contains(text(), 'Write automation scripts')]")
    private static WebElement writeAutomationScripts;

    @FindBy(xpath = "//span[contains(text(), 'Framework set up')]")
    private static WebElement frameworkSetUp;

    @FindBy(id = "target-manual1")
    private static WebElement targetManual1;

    @FindBy(id = "target-manual2")
    private static WebElement targetManual2;

    @FindBy(id = "target-auto1")
    private static WebElement targetAuto1;

    @FindBy(id = "target-auto2")
    private static WebElement targetAuto2;

    @FindBy(id = "DragNDropPageFinishButton")
    private static WebElement dragNDropPageFinishButton;

    @FindBy(xpath = "//div[@class=\"text-lg flex absolute -top-10 right-0 px-10 py-8 shadow-custom transition-opacity duration-500 opacity-100\"]")
    private static WebElement successMessageBlock;

    public DragAndDropPage dragWriteTestCases() {
        wait.until(ExpectedConditions.visibilityOf(writeTestCases)).click();
        actions.dragAndDrop(writeTestCases, targetManual1).perform();
        return this;
    }

    public DragAndDropPage dragTestingRequirements() {
        wait.until(ExpectedConditions.visibilityOf(testingRequirements)).click();
        actions.dragAndDrop(testingRequirements, targetManual2).perform();
        return this;
    }

    public DragAndDropPage dragWriteAutomationScripts() {
        wait.until(ExpectedConditions.visibilityOf(writeAutomationScripts)).click();
        actions.dragAndDrop(writeAutomationScripts, targetAuto1).perform();
        return this;
    }

    public DragAndDropPage dragFrameworkSetUp() {
        wait.until(ExpectedConditions.visibilityOf(frameworkSetUp)).click();
        actions.dragAndDrop(frameworkSetUp, targetAuto2).perform();
        return this;
    }

    public String getSuccessMessageText() {
        return wait.until(ExpectedConditions.visibilityOf(successMessageBlock)).getText();
    }
}