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

import static org.junit.Assert.assertTrue;

class FileUploadTest {

    private static WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/");
        MainPage main = new MainPage(driver);
        main.clickFileUpload();
    }


    @Test
    void dragAndDropUploadShouldSucceed() {
        FileUploadPage fileUploadPage = new FileUploadPage(driver);
        fileUploadPage.dragAndDropUpload("C:\\Users\\Lincoln\\Desktop\\sample.txt");
        Assertions.assertTrue(fileUploadPage.dragDropUploadContainsFilename("sample.txt"), "file name should be displayed");
    }

    @Test
    void standardUploadShouldSucceed() throws AWTException {
        FileUploadPage fileUploadPage = new FileUploadPage(driver);
        WebElement fileInput = driver.findElement(By.id("file-upload")); // Locate the file input
        fileInput.sendKeys("C:\\Users\\Lincoln\\Desktop\\sample.txt");

        fileUploadPage.clickUploadButton();

        Assertions.assertTrue(fileUploadPage.fileUploadedSuccess("sample.txt"), "file name should be displayed");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
