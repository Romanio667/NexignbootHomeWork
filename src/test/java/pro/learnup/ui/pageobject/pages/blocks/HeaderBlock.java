package pro.learnup.ui.pageobject.pages.blocks;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pro.learnup.ui.pageobject.pages.BaseView;
import pro.learnup.ui.pageobject.pages.CartPage;
import pro.learnup.ui.pageobject.pages.PhonesPage;
import pro.learnup.ui.pageobject.pages.elements.Button;

public class HeaderBlock extends BaseView {


    @FindBy(xpath = "//input[contains(@id, 'Username')]")
    private WebElement userNameInput;
    @FindBy(xpath = "//input[contains(@id, 'Password')]")
    private WebElement passwordInput;
    @FindBy(xpath = "//a[.='CART']")
    private WebElement cartButton;

    private final By loginButton = By.xpath("//button[.='LOGIN']"); //можно и так, тогда ленивая инициализация не нужна

    public HeaderBlock(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Авторизоваться пользователем {login} {password}")
    public PhonesPage login(String login, String password) {
        webDriver.findElement(loginButton).click();
        userNameInput.sendKeys(login);
        passwordInput.sendKeys(password);
        new Button(webDriver, "Submit").click();
        new WebDriverWait(webDriver, 1).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='LOGOUT']")));
        return new PhonesPage(webDriver);
    }

    @Step("Перейти в корзину")
    public CartPage goToCart() {
        cartButton.click();
        return new CartPage(webDriver);
    }
}
