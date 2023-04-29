package pro.learnup.api;

import com.github.javafaker.Faker;
import io.restassured.http.Header;
import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
import pro.learnup.api.ext.ApiTestExtension;

import static io.restassured.RestAssured.given;

@DisplayName("/api/auth")
@ExtendWith(ApiTestExtension.class)
public class ApiAuthTest {
    Faker faker = new Faker();
    String userName = faker.name().fullName();

    @Test
    @DisplayName("/api/auth/register: 201: успешное создание юзера")
    void createUserTest() {
        given()
                .body("{\n" +
                        "  \"address\": \"russia\",\n" +
                        "  \"email\": \"sdgrdsg@vas.ru\",\n" +
                        "  \"password\": \"vasya3\",\n" +
                        "  \"phone\": \"8999999999\",\n" +
                        "  \"username\": \"" + userName +"\"\n" +
                        "}")
                .post("/api/auth/register")
                .then()
                .statusCode(201)
                .body("address", Matchers.equalTo("russia"))
                .body("email", Matchers.equalTo("sdgrdsg@vas.ru"))
                .body("phone", Matchers.equalTo("8999999999"))
                .body("username", Matchers.equalTo(userName));
    }

    @Test
    @DisplayName("/api/auth/register: 409: создание существующего пользователя")
    void createUserNegativeTest() {
        given()
                .body("{\n" +
                        "  \"address\": \"russia\",\n" +
                        "  \"email\": \"sdgrdsg@vas.ru\",\n" +
                        "  \"password\": \"vasya3\",\n" +
                        "  \"phone\": \"8999999999\",\n" +
                        "  \"username\": \"vasya3\"\n" +
                        "}")
                .post("/api/auth/register")
                .then()
                .statusCode(409);
    }

    @Test
    @DisplayName("/api/auth/login: 200: успешная авторизация пользователя")
    void loginUserTest() {
        given()
                .body("{\n" +
                        "  \"password\": \"vasya3\",\n" +
                        "  \"username\": \"vasya3\"\n" +
                        "}")
                .post("/api/auth/login")
                .then()
                .statusCode(200)
                .body("username", Matchers.equalTo("vasya3"));
    }

    @Test
    @DisplayName("/api/auth/login: 401: авторизация с неправильными данными")
    void loginUserNegativeTest() {
        given()
                .body("{\n" +
                        "  \"password\": \"vasya57\",\n" +
                        "  \"username\": \"vasya3\"\n" +
                        "}")
                .post("/api/auth/login")
                .then()
                .statusCode(401);
    }
}
