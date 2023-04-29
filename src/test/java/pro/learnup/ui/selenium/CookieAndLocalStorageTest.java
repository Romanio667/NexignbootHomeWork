package pro.learnup.ui.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;

public class CookieAndLocalStorageTest extends BaseTest {

    @Test
    void cookieTest() {
        login();

        assertThat(webDriver.manage().getCookies()).isEmpty();
        Cookie cookie = new Cookie("ourCookie", "12345678");
        webDriver.manage().addCookie(cookie);
        assertThat(webDriver.manage().getCookies()).containsExactlyInAnyOrder(cookie);

        webDriver.manage().deleteAllCookies();

        assertThat(webDriver.manage().getCookies()).isEmpty();
    }

    @Test
    void localStorageTest() {
        webDriver.get("http://localhost:3000/");
        ((WebStorage) webDriver).getLocalStorage().setItem("token", "eyJhbGciOiJIUzI1NiJ9.YWRtaW4.MvnWZ6OdCsCt0asd49VCLvYNEBq5KKoFqdOkDzjxw6s");
        webDriver.navigate().refresh();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='LOGOUT']")));
    }
}
