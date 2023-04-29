package pro.learnup.ui.bdd;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.*;
import org.openqa.selenium.By;
import pro.learnup.ui.selenide.pages.CartPage;
import pro.learnup.ui.selenide.pages.PhonePage;
import pro.learnup.ui.selenide.pages.PhonesPage;
import pro.learnup.ui.selenide.pages.blocks.HeaderBlock;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MyStepdefs {
    @Дано("Пользователь {string} {string} авторизован")
    public void login(String arg0, String arg1) {
        Selenide.open("http://localhost:3000/", PhonesPage.class)
                .getHeaderBlock()
                .login(arg0, arg1);
    }

    @Когда("Пользователь выбирает телефон {string}")
    public void selectPhone(String arg0) {
        page(PhonesPage.class).selectPhone(arg0);
    }

    @Тогда("Пользователь находится на странице телефона {string}")
    public void checkPhoneName(String arg0) {
        Selenide.page(PhonePage.class).checkPhoneName(arg0);
    }

    @Когда("Пользователь нажимает на кнопку {string}")
    public void clickButton(String arg0) {
        $x("//button[.='" + arg0 + "']").click();
    }

    @И("Пользователь переходит в корзину")
    public void goToCart() {
        new HeaderBlock().goToCart();
    }

    @То("В корзине только телефон {string}")
    public void checkCartContainExactly(String arg0) {
        new CartPage().checkCartContainExactly(arg0);
    }

    @Тогда("Отображается текст об успешном оформлении заказа")
    public void checkThatCheckOutIsSuccessful() {
        page(CartPage.class).checkThatCheckOutIsSuccessful();
    }

    @Дано("Открыта базовая страница сайта")
    public void openBasePage() {
        open("http://localhost:3000/");
    }

    @И("Пользователь вводит значения {string} в поле {string}")
    public void input(String arg0, String arg1) {
        $(By.xpath("//input[contains(@id, '"+arg1+"')]")).sendKeys(arg0);
    }

    @Тогда("отображается кнопка {string}")
    public void buttonIsVisible(String arg0) {
        $(byText(arg0)).shouldBe(Condition.visible);
    }
}
