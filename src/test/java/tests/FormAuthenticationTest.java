package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.FormAuthPage;
import pages.MainPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FormAuthenticationTest {

    private static WebDriver driver;

    @BeforeEach
    public void setup() {
        System.out.println("Setting up WebDriver and navigating to the main page...");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/");

        // Navigate to the Form Authentication page
        MainPage main = new MainPage(driver);
        System.out.println("Clicking on Form Authentication link to proceed with login test.");
        main.clickFormAuth();
    }

    @Test
    void verifySuccessfulLoginDisplaysLogoutButton() {
        System.out.println("Starting test: Form authentication should succeed with correct credentials...");

        // Initialize the FormAuthPage and enter login credentials
        FormAuthPage formAuth = new FormAuthPage(driver);
        String username = "tomsmith";
        String password = "SuperSecretPassword!";

        System.out.println("Entering username: " + username);
        formAuth.enterUsername(username);

        System.out.println("Entering password.");
        formAuth.enterPassword(password);

        System.out.println("Clicking login button.");
        formAuth.clickLogin();

        // Assertion with clear message
        System.out.println("Checking if logout button is displayed to confirm successful login.");
        assertTrue(formAuth.loginSuccess(), "Expected logout button to be present, indicating a successful login.");

        System.out.println("Test completed: Login was successful, and logout button is displayed as expected.");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Cleaning up and closing the WebDriver...");
        if (driver != null) {
            driver.quit();
        }
        System.out.println("Teardown complete.");
    }
}
