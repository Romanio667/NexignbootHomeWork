package pro.learnup.ui.selenide;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pro.learnup.ui.selenide.ext.UiTestsExt;
import pro.learnup.ui.selenide.pages.PhonesPage;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.parameter;

@DisplayName("Покупка телефона")
@ExtendWith(UiTestsExt.class)
public class ShopTest {
    String login = "admin";
    String password = "admin";

    @Feature("Покупка телефона")
    @Description("В этом тесте покупаем телефон")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Покупка телефона")
    @ParameterizedTest(name = "Покупка телефона {0}")
    @ValueSource(strings = {"HTC U11", "Apple iPhone X"})
    public void buyPhoneTest(String phoneName) {
        parameter("Название телефона", phoneName);
        open("http://localhost:3000/", PhonesPage.class)
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
