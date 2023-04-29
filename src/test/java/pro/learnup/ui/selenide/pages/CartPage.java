package pro.learnup.ui.selenide.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CartPage extends BasePage {

    private SelenideElement checkOutButton = $(By.xpath("//button[.='Checkout']"));
    private SelenideElement confirmButton = $(By.xpath("//button[.='Confirm']"));
    
    @Step("Проверить, что в корзине только телефоны {phoneName}")
    public CartPage checkCartContainExactly(String... phoneName) {
        $(By.className("cart-items")).$$x(".//table//tbody/tr//a")
                .shouldHave(CollectionCondition.exactTextsCaseSensitiveInAnyOrder(phoneName));
        return this;
    }
    @Step("Нажать на кнопку CheckOut")
    public CartPage clickCheckOut() {
        checkOutButton.click();
        return page(CartPage.class);
    }

    @Step("Нажать на кнопку Confirm")
    public CartPage clickConfirm() {
        confirmButton.click();
        return page(CartPage.class);
    }

    @Step("Проверить, что покупка успешно совершена")
    public CartPage checkThatCheckOutIsSuccessful() {
        $(byText("Your order has been received. The items you've ordered will be sent to your address."))
                .shouldBe(Condition.visible);
        return page(CartPage.class);
    }
}
