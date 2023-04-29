package pro.learnup.ui.selenium;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ShopTest extends BaseTest {

    @ParameterizedTest
    @ValueSource(strings = {"HTC U11"})
    public void buyPhoneTest(String phoneName) {

        login();

        List<WebElement> phones = webDriver.findElements(By.className("product"));
        phones.forEach(p -> System.out.println(p.getText() + "\n\n"));

        phones.stream()
                .filter(el -> el.getText().contains(phoneName))
                .findFirst()
                .orElseThrow()
                .findElement(By.xpath(".//a[.='See more']")).click();

        assertThat(webDriver.findElement(By.cssSelector(".product-details-container h1")).getText())
                .as("Должна открыться страница с телефоном " + phoneName)
                .isEqualTo(phoneName);

        webDriver.findElement(By.xpath("//button[.='Add to cart']")).click();
        webDriver.findElement(By.xpath("//a[.='CART']")).click();

        List<String> actualPhoneList = webDriver.findElement(By.className("cart-items"))
                .findElements(By.xpath(".//table//tbody/tr"))
                .stream()
                .map(el -> el.findElements(By.xpath("./td")).get(1).getText())
                .collect(Collectors.toList());

        assertThat(actualPhoneList).containsExactlyInAnyOrder(phoneName);

        webDriver.findElement(By.xpath("//button[.='Checkout']")).click();

        webDriverWait.until(ExpectedConditions.textToBe(By.xpath("//h1[text()='Checkout Information']/../table//tbody/tr/td[1]"), phoneName));

        webDriver.findElement(By.xpath("//button[.='Confirm']")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[.='OK']")))
                .click();

        webDriver.quit();
    }
}
