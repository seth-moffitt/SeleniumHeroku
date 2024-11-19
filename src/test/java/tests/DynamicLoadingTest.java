package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DynamicLoadingPage;
import pages.MainPage;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DynamicLoadingTest {

    private static WebDriver driver;

    @BeforeEach
    public void setup() {
        System.out.println("Setting up WebDriver and navigating to the main page...");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/");
        MainPage main = new MainPage(driver);
        main.clickDynamicLoading();
        System.out.println("Navigated to the Dynamic Loading page.");
    }

    @Test
    void testHiddenElementVisibility() {
        System.out.println("Starting test: Verify visibility of a hidden element after clicking the trigger button...");

        DynamicLoadingPage dynamicLoadingPage = new DynamicLoadingPage(driver);

        dynamicLoadingPage.clickHiddenElementLink();

        System.out.println("Clicking the button to make the hidden element visible.");
        dynamicLoadingPage.clickStartButton();

        // Wait for the hidden element to become visible
        dynamicLoadingPage.getLoadingBar();

        // Assert that the element is now visible
        Assertions.assertTrue(dynamicLoadingPage.verifyFinishText(), "The hidden element is not visible after triggering the action.");

        System.out.println("Test completed: The hidden element is successfully visible after clicking the trigger button.");
    }

    @Test
    void testDynamicElementRendering() {
        System.out.println("Starting test: Verify rendering of a new element after clicking the trigger button...");

        DynamicLoadingPage dynamicLoadingPage = new DynamicLoadingPage(driver);

        dynamicLoadingPage.clickLateRenderElementLink();

        System.out.println("Clicking the button to trigger the rendering of a new element.");
        dynamicLoadingPage.clickStartButton();

        // Wait for the new element to be present in the DOM
        dynamicLoadingPage.getLoadingBar();

        // Assert that the element is now present and visible
        Assertions.assertTrue(dynamicLoadingPage.verifyFinishText(), "The hidden element is not visible after triggering the action.");

        System.out.println("Test completed: The dynamically rendered element is successfully displayed after clicking the trigger button.");
    }

    @Test
    void testLoadingSpinnerDisappears() {
        System.out.println("Starting test: Verify the loading spinner disappears after the content is loaded...");

        DynamicLoadingPage dynamicLoadingPage = new DynamicLoadingPage(driver);

        dynamicLoadingPage.clickLateRenderElementLink();

        System.out.println("Clicking the button to trigger loading spinner.");
        dynamicLoadingPage.clickStartButton();

        // Wait for the loading spinner to appear
        dynamicLoadingPage.getLoadingBar();

        System.out.println("Waiting for the loading spinner to disappear.");
        // Wait for the loading spinner to disappear
        dynamicLoadingPage.loadingBarInvisibility();

        // Assert that the content is loaded
        Assertions.assertTrue(dynamicLoadingPage.verifyFinishText(), "The content is not displayed after the loading spinner disappears.");

        System.out.println("Test completed: The loading spinner disappeared, and the content is displayed successfully.");
    }

    @Test
    void testNoElementWithoutTrigger() {
        System.out.println("Starting test: Verify no dynamic element is loaded if the trigger button is not clicked...");

        DynamicLoadingPage dynamicLoadingPage = new DynamicLoadingPage(driver);

        dynamicLoadingPage.clickLateRenderElementLink();

        // Check that the dynamic element is not present initially
        System.out.println("Ensuring the dynamic element is not present before triggering.");

        // Assert that the dynamic element list is empty
        Assertions.assertTrue(dynamicLoadingPage.verifyFinishTextInvisibility(), "Dynamic element should not be present before triggering.");

        System.out.println("Test completed: No dynamic element is present before clicking the trigger button.");
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
