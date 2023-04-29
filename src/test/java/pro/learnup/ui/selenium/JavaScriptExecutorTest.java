package pro.learnup.ui.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class JavaScriptExecutorTest extends BaseTest {


    @Test
    void deleteElementTest() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) this.webDriver;

        webDriver.get("https://demoqa.com/modal-dialogs");
        javascriptExecutor.executeScript("arguments[0].click()", webDriver.findElement(By.id("showSmallModal")));
        javascriptExecutor.executeScript("arguments[0].remove()", webDriver.findElement(By.xpath("//div[@role='dialog']")));
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='dialog']")));
    }
}
