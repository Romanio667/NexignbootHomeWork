package pro.learnup.ui.pageobject;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pro.learnup.ui.pageobject.pages.PhonesPage;

import static io.qameta.allure.Allure.parameter;

@DisplayName("Покупка телефона")
public class ShopTest extends BaseTest {
    String login = "admin";
    String password = "admin";

    @Feature("Покупка телефона")
    @Description("В этом тесте покупаем телефон")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Покупка телефона")
    @ParameterizedTest(name = "Покупка телефона {0}")
    @ValueSource(strings = {"HTC U11", "Телефон, которого нет"})
    public void buyPhoneTest(String phoneName) {
        parameter("Название телефона", phoneName);
        webDriver.get("http://localhost:3000/");

        new PhonesPage(webDriver)
                .getHeaderBlock()
                .login(login, password)
                .selectPhone(phoneName)
                .checkPhoneName(phoneName)
                .clickAddToCart()
                .getHeaderBlock()
                .goToCart()
                .checkCartContainExactly(phoneName)
                .clickCheckOut()
                .clickConfirm()
                .checkThatCheckOutIsSuccessful();
    }
}
