package pro.learnup.extensions;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import pro.learnup.config.TestConfig;

public class UiTestsExt implements AfterEachCallback, BeforeAllCallback {

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        Selenide.clearBrowserLocalStorage();
        Selenide.clearBrowserCookies();
        WebDriverRunner.clearBrowserCache();
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        SelenideLogger.addListener("Allure", new AllureSelenide().screenshots(true).savePageSource(true));
        Configuration.baseUrl = ConfigFactory.create(TestConfig.class).baseUrl();
        Configuration.timeout = 2000;
    }
}
