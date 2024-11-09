package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver=driver;
    }

 
    //Locators
    private final By basicAuth = By.xpath("//a[contains(text(),'Basic Auth')]");
    private final By formAuth = By.xpath("//a[contains(text(),'Form Authentication')]");

    //Action Methods
    public void clickAuth() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(basicAuth)).click();
    }
    public void clickFormAuth() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(formAuth)).click();
    }
}
