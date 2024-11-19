package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

public class DynamicLoadingPage {


    private final WebDriver driver;
    private final WebDriverWait wait;

    public DynamicLoadingPage(WebDriver driver) {
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    //Locators
    private final By browseButton = By.cssSelector("#file-upload");
    private final By hiddenElementLink = By.xpath("//a[contains(text(),'Example 1: Element on page that is hidden')]");
    private final By lateRenderElementLink = By.xpath("//a[contains(text(),'Example 1: Element on page that is hidden')]");
    //Have to use xpath for startButton because there are scripts on the page preventing it from being found via css selector
    private final By startButton = By.xpath("//button[contains(text(), 'Start')]");
    private final By loadingBar = By.cssSelector("#loading");
    private final By finishText = By.cssSelector("#finish");

    //Action Methods
    public void clickBrowseButton() {
        wait.until(ExpectedConditions.elementToBeClickable(browseButton)).click();
    }
    public void clickStartButton() {
        wait.until(ExpectedConditions.elementToBeClickable(startButton)).click();
    }
    public void clickHiddenElementLink() {
        wait.until(ExpectedConditions.elementToBeClickable(hiddenElementLink)).click();
    }
    public void clickLateRenderElementLink() {
        wait.until(ExpectedConditions.elementToBeClickable(lateRenderElementLink)).click();
    }
    public void getLoadingBar() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loadingBar));
    }
    public void loadingBarInvisibility() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingBar));
    }
    public boolean verifyFinishText() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(finishText));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    public boolean verifyFinishTextInvisibility() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(finishText));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
