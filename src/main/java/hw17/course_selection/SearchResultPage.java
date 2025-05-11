package hw17.course_selection;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultPage {
    public WebDriver driver;
    public WebDriverWait wait;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h2[contains(text(), 'Unfortunately')]")
    private WebElement searchResult;

    @FindBy(xpath = "//span[contains(text(), 'Back')]")
    private WebElement backArrow;

    public String getSearchResult() {
        wait.until(ExpectedConditions.elementToBeClickable(backArrow));
        wait.until(ExpectedConditions.visibilityOf(searchResult));
        return searchResult.getText();
    }
}