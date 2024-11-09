package tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.FormAuthPage;
import pages.MainPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class FormAuthenticationTest {
    private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/");
        MainPage main = new MainPage(driver);
        main.clickFormAuth();
    }


    @Test
    public void formAuthShouldSucceed() {
        FormAuthPage formAuth = new FormAuthPage(driver);
        formAuth.enterUsername("tomsmith");
        formAuth.enterPassword("SuperSecretPassword!");
        formAuth.clickLogin();
        assertTrue("logout button should be present", formAuth.loginSuccess());;
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
