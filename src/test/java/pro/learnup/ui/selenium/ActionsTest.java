package pro.learnup.ui.selenium;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

public class ActionsTest extends BaseTest {
    @Test
    void dragAndDropActionsTest() {
        webDriver.get("https://demoqa.com/sortable");

        List<WebElement> blocks = webDriver.findElements(By.xpath("//div[contains(@class,'vertical-list-container')]//div[contains(@class, 'list-group-item')]"));

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(blocks.get(0).getText()).isEqualTo("One");
        softAssertions.assertThat(blocks.get(1).getText()).isEqualTo("Two");
        softAssertions.assertThat(blocks.get(2).getText()).isEqualTo("Three");
        softAssertions.assertThat(blocks.get(3).getText()).isEqualTo("Four");
        softAssertions.assertThat(blocks.get(4).getText()).isEqualTo("Five");
        softAssertions.assertThat(blocks.get(5).getText()).isEqualTo("Six");
        softAssertions.assertAll();


        Actions actions = new Actions(webDriver);

        actions.moveToElement(blocks.get(0))
                .clickAndHold()
                .pause(Duration.ofSeconds(1))
                .moveToElement(blocks.get(5))
                .release()
                .build()
                .perform();

        softAssertions.assertThat(blocks.get(0).getText()).isEqualTo("Two");
        softAssertions.assertThat(blocks.get(1).getText()).isEqualTo("Three");
        softAssertions.assertThat(blocks.get(2).getText()).isEqualTo("Four");
        softAssertions.assertThat(blocks.get(3).getText()).isEqualTo("Five");
        softAssertions.assertThat(blocks.get(4).getText()).isEqualTo("Six");
        softAssertions.assertThat(blocks.get(5).getText()).isEqualTo("One");
        softAssertions.assertAll();

        actions.dragAndDrop(blocks.get(0), blocks.get(5))
                .build()
                .perform();

        softAssertions.assertThat(blocks.get(0).getText()).isEqualTo("Three");
        softAssertions.assertThat(blocks.get(1).getText()).isEqualTo("Four");
        softAssertions.assertThat(blocks.get(2).getText()).isEqualTo("Five");
        softAssertions.assertThat(blocks.get(3).getText()).isEqualTo("Six");
        softAssertions.assertThat(blocks.get(4).getText()).isEqualTo("One");
        softAssertions.assertThat(blocks.get(5).getText()).isEqualTo("Two");
        softAssertions.assertAll();
    }
}
