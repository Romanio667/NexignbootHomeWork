package pro.learnup.api;

import com.github.javafaker.Faker;
import io.restassured.http.Header;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pro.learnup.api.ext.ApiTestExtension;

import static io.restassured.RestAssured.given;

@DisplayName("/api/user")
@ExtendWith(ApiTestExtension.class)
public class ApiUserTest {

    String token;
    String userName;
    String id;

    @BeforeEach
    void setUp() {
        userName = new Faker().name().fullName();

        token = given()
                .body("{\n" +
                        "  \"address\": \"russia\",\n" +
                        "  \"email\": \"sdgrdsg@vas.ru\",\n" +
                        "  \"password\": \"vasya2\",\n" +
                        "  \"phone\": \"8999999999\",\n" +
                        "  \"username\": \"" + userName + "\"\n" +
                        "}")
                .post("/api/auth/register")
                .then()
                .statusCode(201)
                .body("address", Matchers.equalTo("russia"))
                .body("email", Matchers.equalTo("sdgrdsg@vas.ru"))
                .body("phone", Matchers.equalTo("8999999999"))
                .body("username", Matchers.equalTo(userName))
                .extract()
                .jsonPath()
                .getString("token");
    }
    @Test
    @DisplayName("/api/user: 200: получение информации о юзере авторизованным пользователем")
    void successfulGetUserTest() {
       id = given()
                .header(new Header("Authorization", "Bearer " + token))
                .get("/api/user")
                .then()
                .statusCode(200)
                .body("address", Matchers.equalTo("russia"))
                .body("email", Matchers.equalTo("sdgrdsg@vas.ru"))
                .body("phone", Matchers.equalTo("8999999999"))
                .body("username", Matchers.equalTo(userName))
               .extract()
               .jsonPath()
               .getString("id");
    }

    @Test
    @DisplayName("/api/user: 403: получение информации о юзере без авторизации")
    void GetUserNegativeTest() {
       given()
                .get("/api/user")
                .then()
                .statusCode(403);
    }
    @Test
    @DisplayName("/api/user: 200: редактирование данных о пользователе")
    void successfulPutUserTest() {
        given()
                .header(new Header("Authorization", "Bearer " + token))
                .body("{\n" +
                        "  \"address\": \"russia\",\n" +
                        "  \"email\": \"sdgrdsg@vas.ru\",\n" +
                        "  \"id\": \"61f2a53cc4ab0f0013c9ed2c\",\n" +
                        "  \"orders\": [],\n" +
                        "  \"password\": \"$2b$10$v6VKDjuvznEODNF37zqPUOmRrRRM.W4CJ/JyWkIyKNviQ5YI2tfmG\",\n" +
                        "  \"phone\": \"8999999999\",\n" +
                        "  \"token\": \"eyJhbGciOiJIUzI1NiJ9.dmFzeWE.-q2q3ipjyOTT9UiZzfILKKZTDGaGBPBr9hGImF-wqD8\",\n" +
                        "  \"username\": \"vasya\"\n" +
                        "}")
                .put("/api/user")
                .then()
                .statusCode(200)
                .body("message", Matchers.equalTo("User updated"));
    }
}