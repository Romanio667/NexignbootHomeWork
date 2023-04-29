package pro.learnup.ui.pageobject.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PhonesPage extends BasePage  {

    public PhonesPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Выбрать телефон {phoneName}")
    public PhonePage selectPhone(String phoneName) {
        List<WebElement> phones = webDriver.findElements(By.className("product"));
        phones.stream()
                .filter(el -> el.getText().contains(phoneName))
                .findFirst()
                .orElseThrow()
                .findElement(By.xpath(".//a[.='See more']")).click();
        return new PhonePage(webDriver);
    }
}
