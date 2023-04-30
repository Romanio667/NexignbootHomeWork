package pro.learnup.steps;

import com.codeborne.selenide.Selenide;
import pro.learnup.pages.Page;
import pro.learnup.testdata.User;

import java.text.MessageFormat;

import static com.codeborne.selenide.Selenide.open;

public class UiSteps {
    public static <T> T openPage(Class<T> pageClass) {
        String url = pageClass.getAnnotation(Page.class).url();
        return open(url, pageClass);
    }

    public static <T> T openPage(Class<T> pageClass, String... parameters) {
        String url = pageClass.getAnnotation(Page.class).url();
        String formattedUrl = MessageFormat.format(url, parameters);
        return open(formattedUrl, pageClass);
    }

    public static <T> T openPage(User user, Class<T> pageClass, String... parameters) {
        open("/");
        Selenide.localStorage().setItem("token", user.getToken());
        return openPage(pageClass, parameters);
    }

    public static <T> T openPage(User user, Class<T> pageClass) {
        open("/");
        Selenide.localStorage().setItem("token", user.getToken());
        return openPage(pageClass);
    }
}
