package pro.learnup.ui.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;

    protected void login() {
        webDriver.get("http://localhost:3000/");

        webDriver.findElement(By.xpath("//button[.='LOGIN']")).click();
        webDriver.findElement(By.xpath("//input[contains(@id, 'Username')]")).sendKeys("admin");
        webDriver.findElement(By.xpath("//input[contains(@id, 'Password')]")).sendKeys("admin");
        webDriver.findElement(By.xpath("//button[.='Submit']")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='LOGOUT']")));
    }

    @BeforeEach
    void setUp() {

//        ChromeOptions chromeOptions = new ChromeOptions().addArguments("--blink-settings=imagesEnabled=false");
        webDriver = WebDriverManager.chromedriver().create();
//        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
//        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(webDriver, 3);
    }

    @AfterEach
    void tearDown() {
        webDriver.quit();
    }

}
