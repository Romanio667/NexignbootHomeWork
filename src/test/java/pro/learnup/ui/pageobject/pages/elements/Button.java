package pro.learnup.ui.pageobject.pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;

import static io.qameta.allure.Allure.step;

public class Button implements WrapsElement {
    private WebElement webElement;
    private String name;

    public Button(WebDriver webDriver, String name) {
        this.webElement = webDriver.findElement(By.xpath("//button[.='" + name + "']"));
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
