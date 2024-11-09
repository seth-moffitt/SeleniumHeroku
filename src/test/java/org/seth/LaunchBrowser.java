package org.seth;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import tests.BasicAuthTest;


public class LaunchBrowser {
    public static void main(String[] args) {
//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();
////        WebDriverManager.firefoxdriver().setup();
////        WebDriver driver = new FirefoxDriver();
////        WebDriverManager.edgedriver().setup();
////        WebDriver driver = new EdgeDriver();
//        driver.get("https://the-internet.herokuapp.com/");
//        System.out.println("Page title is: " + driver.getTitle());
//        driver.quit();
        BasicAuthTest basicAuthTest = new BasicAuthTest();
        basicAuthTest.basicAuthShouldSucceed();
    }
}
