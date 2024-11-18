package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BasicAuthTest {

    private static WebDriver driver;

    @BeforeEach
    public void setup() {
        System.out.println("Setting up WebDriver and navigating to the main page...");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/");
        System.out.println("Navigated to the main page.");
    }

    @Test
    void verifySuccessfulBasicAuthLoginDisplaysSuccessMessage() {
        System.out.println("Starting test: Basic Auth should succeed with correct credentials...");

        // Navigate to Basic Auth link from the main page
        MainPage main = new MainPage(driver);
        System.out.println("Clicking on the Basic Auth link on the main page.");
        main.clickAuth();

        // Access the Basic Auth page with credentials
        String authUrl = "https://admin:admin@the-internet.herokuapp.com/basic_auth";
        System.out.println("Attempting to log in with Basic Auth credentials via URL: " + authUrl);
        driver.get(authUrl);

        // Locate and verify success message on the page
        WebElement successMessage = driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credenti')]"));
        System.out.println("Checking if the success message is displayed after login.");

        // Assertion with clear message for failure
        assertTrue(successMessage.isDisplayed(),
                "Expected success message to be displayed after Basic Auth login with correct credentials.");

        System.out.println("Test completed: Success message is displayed as expected after Basic Auth login.");
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
