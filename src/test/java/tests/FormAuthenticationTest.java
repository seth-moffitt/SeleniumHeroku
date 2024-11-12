package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.FormAuthPage;
import pages.MainPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

class FormAuthenticationTest {
    private static WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/");
        MainPage main = new MainPage(driver);
        main.clickFormAuth();
    }


    @Test
    void formAuthShouldSucceed() {
        FormAuthPage formAuth = new FormAuthPage(driver);
        formAuth.enterUsername("tomsmith");
        formAuth.enterPassword("SuperSecretPassword!");
        formAuth.clickLogin();
        Assertions.assertTrue(formAuth.loginSuccess(), "logout button should be present");;
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
