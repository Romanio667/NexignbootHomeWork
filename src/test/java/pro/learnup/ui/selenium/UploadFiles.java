package pro.learnup.ui.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class UploadFiles extends BaseTest {

    @Test
    void uploadTest() throws InterruptedException {
        webDriver.get("http://the-internet.herokuapp.com/upload");

        webDriver.findElement(By.xpath("//input[@type='file' and @name='file']"))
                .sendKeys("/Users/dimakar/learnup/learn-up-ui-autotests/src/test/resources/chromedriver");

        Thread.sleep(5000);
    }
}
