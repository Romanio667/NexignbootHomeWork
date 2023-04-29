package pro.learnup.ui.selenide.pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;

public class PhonesPage extends BasePage {

    @Step("Выбрать телефон {phoneName}")
    public PhonePage selectPhone(String phoneName) {
        ElementsCollection phones = $$(By.className("product"));
        phones.stream()
                .filter(el -> el.getText().contains(phoneName))
                .findFirst()
                .orElseThrow()
                .findElement(By.xpath(".//a[.='See more']")).click();
        return page(PhonePage.class);
    }
}
