package pro.learnup.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@Page(url = "/product/{0}")
public class PhonePage extends BasePage {

    private SelenideElement addToCartButton = $(By.xpath("//button[.='Add to cart']"));

    

    @Step("Проверить, что открыта страница {phoneName}")
    public PhonePage checkPhoneName(String phoneName) {
        $(By.cssSelector(".product-details-container h1"))
                .shouldBe(and("Exact text", visible, exactText(phoneName))
                        .because("Должна открыться страница с телефоном " + phoneName));
        return this;
    }

    @Step("Нажать на кнопку Add to Cart")
    public PhonePage clickAddToCart() {
        addToCartButton.click();
        return this;
    }

    @Step("Нажать на кнопку Add to Cart")
    public PhonePage checkSuccessfulPhoneAddedToCart() {
        $(byText("Item added to your cart.")).shouldBe(visible);
        return this;
    }


}
