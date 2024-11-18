package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.FileUploadPage;
import pages.MainPage;

import java.awt.*;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FileUploadTest {

    private static WebDriver driver;

    @BeforeEach
    public void setup() {
        System.out.println("Setting up WebDriver and navigating to the main page...");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/");

        // Navigate to File Upload page
        MainPage main = new MainPage(driver);
        main.clickFileUpload();
        System.out.println("Navigated to the File Upload page.");
    }

    @Test
    void verifyDragAndDropFileUploadDisplaysFilename() {
        System.out.println("Starting test: Drag-and-drop file upload should display the filename...");

        FileUploadPage fileUploadPage = new FileUploadPage(driver);

        // Log action: drag and drop file
        String filePath = "C:\\Users\\Lincoln\\Desktop\\sample.txt";
        System.out.println("Attempting to upload file via drag and drop: " + filePath);
        fileUploadPage.dragAndDropUpload(filePath);

        // Assertion with clear message
        String expectedFilename = "sample.txt";
        assertTrue(fileUploadPage.dragDropUploadContainsFilename(expectedFilename),
                "Expected filename '" + expectedFilename + "' to be displayed after drag-and-drop upload.");

        System.out.println("Test completed: Filename '" + expectedFilename + "' is correctly displayed on the page after drag-and-drop upload.");
    }

    @Test
    void verifyStandardFileUploadDisplaysFilename() throws AWTException {
        System.out.println("Starting test: Standard file upload should display the filename...");

        FileUploadPage fileUploadPage = new FileUploadPage(driver);

        // Locate the file input element
        String filePath = "C:\\Users\\Lincoln\\Desktop\\sample.txt";
        WebElement fileInput = driver.findElement(By.id("file-upload"));
        System.out.println("Locating file input field and uploading file: " + filePath);
        fileInput.sendKeys(filePath);

        // Click the upload button
        System.out.println("Clicking upload button to submit the file.");
        fileUploadPage.clickUploadButton();

        // Assertion with clear message
        String expectedFilename = "sample.txt";
        assertTrue(fileUploadPage.fileUploadedSuccess(expectedFilename),
                "Expected filename '" + expectedFilename + "' to be displayed after standard upload.");

        System.out.println("Test completed: Filename '" + expectedFilename + "' is correctly displayed on the page after standard upload.");
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
