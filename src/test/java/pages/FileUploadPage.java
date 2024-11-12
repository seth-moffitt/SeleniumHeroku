package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.time.Duration;

public class FileUploadPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public FileUploadPage(WebDriver driver) {
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    //Locators
    private final By browseButton = By.cssSelector("#file-upload");
    private final By dragDropUpload = By.cssSelector("#drag-drop-upload");
    private final By uploadButton = By.cssSelector("#file-submit");
    private final By fileUploadedMessage = By.xpath("//h3[contains(text(),'File Uploaded!')]");
    private final By fileUploadedFilename = By.cssSelector("#uploaded-files");

    //Action Methods
    public void dragAndDropUpload(String filePath) {
        WebElement dropTarget = driver.findElement(dragDropUpload);

        // JavaScript to simulate HTML5 drag-and-drop
        String script = "var target = arguments[0]; " +
                "var dataTransfer = new DataTransfer();" +
                "dataTransfer.items.add(new File([''], '" + Paths.get(filePath).getFileName() + "'));" +
                "target.dispatchEvent(new DragEvent('drop', {dataTransfer: dataTransfer}));";

        ((JavascriptExecutor) driver).executeScript(script, dropTarget);
    }

    public void clickUploadButton() {
        wait.until(ExpectedConditions.elementToBeClickable(uploadButton)).click();
    }

    public boolean fileUploadedSuccess(String fileName) {
        try {
            WebElement fileUploadedName = driver.findElement(fileUploadedFilename);
            wait.until(ExpectedConditions.presenceOfElementLocated(fileUploadedMessage));
            wait.until(ExpectedConditions.textToBePresentInElement(fileUploadedName, fileName));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean dragDropUploadContainsFilename(String fileName) {
        try {
            WebElement fileUploadedName = driver.findElement(dragDropUpload);
            wait.until(ExpectedConditions.textToBePresentInElement(fileUploadedName, fileName));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
