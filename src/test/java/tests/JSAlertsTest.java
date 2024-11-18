package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.JSAlertsPage;
import pages.MainPage;

import java.time.Duration;

public class JSAlertsTest {

    private static WebDriver driver;

    @BeforeEach
    public void setup() {
        System.out.println("Setting up WebDriver and navigating to the main page...");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/");

        // Navigate to JavaScript Alerts page
        MainPage main = new MainPage(driver);
        System.out.println("Clicking on JavaScript Alerts link to proceed with alert tests.");
        main.clickJSAlerts();
        System.out.println("Navigated to the JavaScript Alerts page.");
    }

    @Test
    void clickJSAlertAndVerifyOutput() {
        System.out.println("Starting test: JavaScript alert should display and output the correct message upon dismissal...");

        // Initialize JSAlertsPage and trigger the JS Alert
        JSAlertsPage jsAlertsPage = new JSAlertsPage(driver);
        System.out.println("Clicking on the JavaScript Alert button.");
        jsAlertsPage.clickJSAlertButton();

        // Switch to the alert
        Alert alert = driver.switchTo().alert();

        // Log alert message
        System.out.println("Alert message: " + alert.getText());

        // Accept the alert (click OK)
        System.out.println("Accepting the alert.");
        alert.accept();

        // Retrieve and assert the result text
        String textboxResult = jsAlertsPage.getResultTextboxContents();
        System.out.println("Verifying the result text displayed after alert is closed.");
        Assertions.assertEquals("You successfully clicked an alert", textboxResult, "Expect 'Result' text area to show success message");

        System.out.println("Test completed: The result text area displays the expected success message after alert acceptance.");
    }

    @Test
    void handleJSConfirmAcceptAndVerifyOutput() {
        System.out.println("Starting test: JavaScript Confirm Alert - Verifying output for 'OK' action...");

        // Navigate to JS Alerts page and trigger the Confirm Alert
        JSAlertsPage jsAlertsPage = new JSAlertsPage(driver);
        System.out.println("Triggering JavaScript Confirm Alert by clicking the button.");
        jsAlertsPage.clickJSConfirmButton();

        // Switch to the Confirm Alert dialog
        Alert confirmAlert = driver.switchTo().alert();
        System.out.println("Confirm Alert text displayed: " + confirmAlert.getText());

        // Accept the Confirm Alert (simulate clicking 'OK')
        System.out.println("Accepting the Confirm Alert (clicking 'OK').");
        confirmAlert.accept();

        // Verify the result message displayed on the page
        String resultText = jsAlertsPage.getResultTextboxContents();
        System.out.println("Validating the result text after accepting the Confirm Alert.");
        Assertions.assertEquals("You clicked: Ok", resultText, "Expected result text for 'OK' action is incorrect.");

        System.out.println("Test completed: Result text correctly reflects 'OK' action for JavaScript Confirm Alert.");
    }

    @Test
    void handleJSConfirmDismissAndVerifyOutput() {
        System.out.println("Starting test: JavaScript Confirm Alert - Verifying output for 'Cancel' action...");

        // Navigate to JS Alerts page and trigger the Confirm Alert
        JSAlertsPage jsAlertsPage = new JSAlertsPage(driver);
        System.out.println("Triggering JavaScript Confirm Alert by clicking the button.");
        jsAlertsPage.clickJSConfirmButton();

        // Switch to the Confirm Alert dialog
        Alert confirmAlert = driver.switchTo().alert();
        System.out.println("Confirm Alert text displayed: " + confirmAlert.getText());

        // Dismiss the Confirm Alert (simulate clicking 'Cancel')
        System.out.println("Dismissing the Confirm Alert (clicking 'Cancel').");
        confirmAlert.dismiss();

        // Verify the result message displayed on the page
        String resultText = jsAlertsPage.getResultTextboxContents();
        System.out.println("Validating the result text after dismissing the Confirm Alert.");
        Assertions.assertEquals("You clicked: Cancel", resultText, "Expected result text for 'Cancel' action is incorrect.");

        System.out.println("Test completed: Result text correctly reflects 'Cancel' action for JavaScript Confirm Alert.");
    }


    @Test
    void handleJSPromptInputAcceptAndVerifyOutput() {
        System.out.println("Starting test: JavaScript Prompt - Verifying output after entering text and clicking 'OK'...");

        // Navigate to JS Alerts page and trigger the Prompt Alert
        JSAlertsPage jsAlertsPage = new JSAlertsPage(driver);
        System.out.println("Triggering JavaScript Prompt Alert by clicking the button.");
        jsAlertsPage.clickJSPromptButton();

        // Switch to the Prompt Alert dialog
        Alert promptAlert = driver.switchTo().alert();
        System.out.println("Prompt Alert message displayed: " + promptAlert.getText());

        // Enter text into the Prompt Alert
        String inputText = "OK";
        System.out.println("Entering text into the Prompt Alert: " + inputText);
        promptAlert.sendKeys(inputText);

        // Accept the Prompt Alert (click 'OK')
        System.out.println("Accepting the Prompt Alert (clicking 'OK').");
        promptAlert.accept();

        // Verify the result message displayed on the page
        String resultText = jsAlertsPage.getResultTextboxContents();
        System.out.println("Validating the result text after accepting the Prompt Alert.");
        Assertions.assertEquals("You entered: " + inputText, resultText, "Expected result text does not match the entered input.");

        System.out.println("Test completed: Result text correctly reflects the entered input after clicking 'OK' on the Prompt Alert.");
    }

    @Test
    void handleJSPromptDismissAndVerifyOutput() {
        System.out.println("Starting test: JavaScript Prompt - Verifying output after dismissing the prompt...");

        // Navigate to JS Alerts page and trigger the Prompt Alert
        JSAlertsPage jsAlertsPage = new JSAlertsPage(driver);
        System.out.println("Triggering JavaScript Prompt Alert by clicking the button.");
        jsAlertsPage.clickJSPromptButton();

        // Switch to the Prompt Alert dialog
        Alert promptAlert = driver.switchTo().alert();
        System.out.println("Prompt Alert message displayed: " + promptAlert.getText());

        // Enter text into the Prompt Alert (optional, but it won't be processed when dismissed)
        String inputText = "Cancel";
        System.out.println("Entering text into the Prompt Alert (will be discarded): " + inputText);
        promptAlert.sendKeys(inputText);

        // Dismiss the Prompt Alert (click 'Cancel')
        System.out.println("Dismissing the Prompt Alert (clicking 'Cancel').");
        promptAlert.dismiss();

        // Verify the result message displayed on the page
        String resultText = jsAlertsPage.getResultTextboxContents();
        System.out.println("Validating the result text after dismissing the Prompt Alert.");
        Assertions.assertEquals("You entered: null", resultText, "Expected result text does not match for dismissed prompt.");

        System.out.println("Test completed: Result text correctly reflects the dismissal of the Prompt Alert.");
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
