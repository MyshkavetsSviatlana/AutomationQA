package hw17.course_selection;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CourseSelectionPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CourseSelectionPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@title = \"Select country\"]")
    private WebElement selectCountry;

    @FindBy(xpath = "//*[@title = \"Select language\"]")
    private WebElement selectLanguage;

    @FindBy(xpath = "//*[@title = \"Select type\"]")
    private WebElement selectType;

    @FindBy(xpath = "//*[@title=\"Start date\"]")
    private WebElement calendarFrom;

    @FindBy(xpath = "//*[@title=\"End date\"]")
    private WebElement calendarTo;

    @FindBy(id = "MultipleSelect")
    private WebElement selectCourse;

    @FindBy(xpath = "//*[@type= \"submit\"]")
    private WebElement submitButton;

    public int getIndex(Select select, String value) {
        List<WebElement> listOfOptions = select.getOptions();
        int index = 0;
        for (WebElement option : listOfOptions) {
            if (!option.getText().equals(value)) {
                index++;
            } else {
                break;
            }
        }
        return index;
    }

    public CourseSelectionPage selectCountry(String value) {
        wait.until(ExpectedConditions.visibilityOf(selectCountry)).click();
        Select countrySelect = new Select(selectCountry);
        System.out.println(countrySelect);
        int index = getIndex(countrySelect, value);
        countrySelect.selectByIndex(index);
        return this;
    }

    public CourseSelectionPage selectLanguage(String value) {
        wait.until(ExpectedConditions.visibilityOf(selectLanguage)).click();
        Select languageSelect = new Select(selectLanguage);
        int index = getIndex(languageSelect, value);
        languageSelect.selectByIndex(index);
        return this;
    }

    public CourseSelectionPage selectType(String value) {
        wait.until(ExpectedConditions.visibilityOf(selectType)).click();
        Select typeSelect = new Select(selectType);
        int index = getIndex(typeSelect, value);
        typeSelect.selectByIndex(index);
        return this;
    }

    public CourseSelectionPage selectDateFrom(String value) {
        wait.until(ExpectedConditions.visibilityOf(calendarFrom)).sendKeys(value);
        return this;
    }

    public CourseSelectionPage selectDateTo(String value) {
        wait.until(ExpectedConditions.visibilityOf(calendarTo)).sendKeys(value);
        return this;
    }

    public CourseSelectionPage selectCourse(String value) {
        wait.until(ExpectedConditions.visibilityOf(selectCourse));
        Select courseSelect = new Select(selectCourse);
        courseSelect.selectByValue(value);
        return this;
    }

    public void submitForm() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }
}