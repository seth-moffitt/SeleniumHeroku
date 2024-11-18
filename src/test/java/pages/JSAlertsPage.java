package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.time.Duration;

public class JSAlertsPage {

//    private final WebDriver driver;
    private final WebDriverWait wait;

    public JSAlertsPage(WebDriver driver) {
//        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    //Locators
    private final By jsAlertButton = By.xpath("//button[@onclick='jsAlert()']");
    private final By jsConfirmButton = By.xpath("//button[@onclick='jsConfirm()']");
    private final By jsPromptButton = By.xpath("//button[@onclick='jsPrompt()']");
    private final By jsResultTextbox = By.cssSelector("#result");

    //Action Methods
    public void clickJSAlertButton() {
        wait.until(ExpectedConditions.elementToBeClickable(jsAlertButton)).click();
    }

    public void clickJSConfirmButton() {
        wait.until(ExpectedConditions.elementToBeClickable(jsConfirmButton)).click();
    }

    public void clickJSPromptButton() {
        wait.until(ExpectedConditions.elementToBeClickable(jsPromptButton)).click();
    }

    public String getResultTextboxContents() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(jsResultTextbox)).getText();
    }
}
