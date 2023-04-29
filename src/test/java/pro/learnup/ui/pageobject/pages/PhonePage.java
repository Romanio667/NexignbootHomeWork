package pro.learnup.ui.pageobject.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class PhonePage extends BasePage  {

    @FindBy(xpath = "//button[.='Add to cart']")
    private WebElement addToCartButton;

    public PhonePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Проверить, что открыта страница {phoneName}")
    public PhonePage checkPhoneName(String phoneName) {
        assertThat(webDriver.findElement(By.cssSelector(".product-details-container h1")).getText())
                .as("Должна открыться страница с телефоном " + phoneName)
                .isEqualTo(phoneName);
        return this;
    }

    @Step("Нажать на кнопку Add to Cart")
    public PhonePage clickAddToCart() {
        addToCartButton.click();
        return this;
    }


}
