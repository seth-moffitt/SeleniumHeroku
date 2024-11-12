package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

class BasicAuthTest {

    private static WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/");
    }


    @Test
    void basicAuthShouldSucceed() {
        MainPage main = new MainPage(driver);
        main.clickAuth();
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
        //Since this page has only this one element I care about, there is no page file for it
        WebElement successMessage = driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credenti')]"));
        Assertions.assertTrue(successMessage.isDisplayed(), "success message should be displayed");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
