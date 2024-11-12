package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

//    private final WebDriver driver;
    private final WebDriverWait wait;

    public MainPage(WebDriver driver) {
//        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

 
    //Locators
    private final By basicAuth = By.xpath("//a[contains(text(),'Basic Auth')]");
    private final By formAuth = By.xpath("//a[contains(text(),'Form Authentication')]");
    private final By fileUpload = By.xpath("//a[contains(text(),'File Upload')]");

    //Action Methods
    public void clickAuth() {
        wait.until(ExpectedConditions.elementToBeClickable(basicAuth)).click();
    }
    public void clickFormAuth() {
        wait.until(ExpectedConditions.elementToBeClickable(formAuth)).click();
    }
    public void clickFileUpload () {
        wait.until(ExpectedConditions.elementToBeClickable(fileUpload)).click();
    }
}
