package pro.learnup.ui.pageobject;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pro.learnup.ui.pageobject.pages.PhonesPage;


import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Авторизация пользователя")
public class AuthTest extends BaseTest {
    String login = "admin";
    String password = "admin";

    @Feature("Авторизация пользователя")
    @Description("В этом тесте авторизуемся")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Авторизация пользователя")
    @Test
    public void authTest() {
        webDriver.get("http://localhost:3000/");
        new PhonesPage(webDriver)
                .getHeaderBlock()
                .login(login, password);
    }


    @Description("Специально упавший тест")
    @DisplayName("Красный тест")
    @Test
    public void failedTest() {
        webDriver.get("http://localhost:3000/");
    }

    @Description("Специально упавший тест")
    @DisplayName("Серый тест")
    @Test
    @Disabled("Тест не прогоняется")
    public void greyTest() {
        webDriver.get("http://localhost:3000/");
    }
}
