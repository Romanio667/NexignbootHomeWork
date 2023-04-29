package pro.learnup.ui.selenide;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pro.learnup.ui.selenide.ext.UiTestsExt;
import pro.learnup.ui.selenide.pages.PhonesPage;

import static com.codeborne.selenide.Selenide.open;

@DisplayName("Авторизация пользователя")
@ExtendWith(UiTestsExt.class)
public class AuthTest {
    String login = "admin";
    String password = "admin";

    @Feature("Авторизация пользователя")
    @Description("В этом тесте авторизуемся")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Авторизация пользователя")
    @Test
    public void authTest() {
        open("http://localhost:3000/", PhonesPage.class)
                .getHeaderBlock()
                .login(login, password);
    }
}
