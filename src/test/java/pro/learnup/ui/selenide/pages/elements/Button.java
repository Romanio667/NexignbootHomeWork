package pro.learnup.ui.selenide.pages.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;

import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.step;

public class Button implements WrapsElement {
    private WebElement webElement;
    private String name;

    public Button(String name) {
        this.webElement = $x("//button[.='" + name + "']");
        this.name = name;
    }

    @Override
    public WebElement getWrappedElement() {
        return this.webElement;
    }

    public Button click() {
        step("Нажать на кнопку " + name, () ->
                getWrappedElement().click());
        return this;
    }

}
