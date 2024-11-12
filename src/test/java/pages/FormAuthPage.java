package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FormAuthPage {

//    private final WebDriver driver;
    private final WebDriverWait wait;

    public FormAuthPage(WebDriver driver) {
//        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    //Locators
    private final By usernameField = By.cssSelector("#username");
    private final By passwordField = By.cssSelector("#password");
    private final By loginButton = By.xpath("//button[@type='submit']");
    private final By logoutButton = By.xpath("//a[@class='button secondary radius']");
    private final By successMessage = By.cssSelector("#flash");

    //Action Methods
    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }
    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
    }
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }
    public boolean loginSuccess() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(successMessage));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

}
