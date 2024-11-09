package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FormAuthPage {

    private final WebDriver driver;

    public FormAuthPage(WebDriver driver) {
        this.driver=driver;
    }


    //Locators
    private final By usernameField = By.cssSelector("#username");
    private final By passwordField = By.cssSelector("#password");
    private final By loginButton = By.xpath("//button[@type='submit']");
    private final By logoutButton = By.xpath("//a[@class='button secondary radius']");
    private final By successMessage = By.cssSelector("#flash");

    //Action Methods
    public void clickLogin() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }
    public void clickLogout() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }
    public void enterUsername(String username) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
    }
    public void enterPassword(String password) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }
    public boolean loginSuccess() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.presenceOfElementLocated(successMessage));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

}
