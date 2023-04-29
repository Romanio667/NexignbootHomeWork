package pro.learnup.ui.selenide.pages.blocks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pro.learnup.ui.selenide.pages.CartPage;
import pro.learnup.ui.selenide.pages.PhonesPage;
import pro.learnup.ui.selenide.pages.elements.Button;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class HeaderBlock {

    private SelenideElement userNameInput = $(By.xpath("//input[contains(@id, 'Username')]"));
    private SelenideElement passwordInput = $(By.xpath("//input[contains(@id, 'Password')]"));
    private SelenideElement cartButton = $(By.xpath("//a[.='CART']"));
    private SelenideElement loginButton = $(By.xpath("//button[.='LOGIN']"));

    @Step("Авторизоваться пользователем {login} {password}")
    public PhonesPage login(String login, String password) {
        loginButton.click();
        userNameInput.sendKeys(login);
        passwordInput.sendKeys(password);
        new Button("Submit").click();
        $(byText("LOGOUT")).shouldBe(Condition.visible);
        return page(PhonesPage.class);
    }

    @Step("Перейти в корзину")
    public CartPage goToCart() {
        cartButton.click();
        return page(CartPage.class);
    }
}
