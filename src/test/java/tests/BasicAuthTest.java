package tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class BasicAuthTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/");
    }


    @Test
    public void basicAuthShouldSucceed() {
        MainPage main = new MainPage(driver);
        main.clickAuth();
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
        //Since this page has only this one element I care about, there is no page file for it
        WebElement successMessage = driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credenti')]"));
        assertTrue("success message should be displayed", successMessage.isDisplayed());
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
