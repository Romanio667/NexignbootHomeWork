package pro.learnup.tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pro.learnup.extensions.UiTest;
import pro.learnup.extensions.UiTestsExt;
import pro.learnup.pages.PhonesPage;
import pro.learnup.testdata.ApiTestDataHelper;
import pro.learnup.testdata.DbTestDataHelper;
import pro.learnup.testdata.User;

import static com.codeborne.selenide.Selenide.open;

@DisplayName("Авторизация пользователя")
@UiTest
public class AuthTest {
    User user;

    @BeforeEach
    void setUp() {
        user = ApiTestDataHelper.createTestUser();
    }

    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Авторизация пользователя")
    @Test
    public void authTest() {
        open("/", PhonesPage.class)
                .getHeaderBlock()
                .login(user);
    }

    @AfterEach
    void tearDown() {
        DbTestDataHelper.deleteUser(user.getId());
    }
}
