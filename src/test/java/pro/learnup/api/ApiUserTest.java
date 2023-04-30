package pro.learnup.api;

import com.github.javafaker.Faker;
import io.restassured.http.Header;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import pro.learnup.api.dto.UserDto;
import pro.learnup.api.endpoints.ApiAuthRegisterEndpoint;
import pro.learnup.api.endpoints.ApiUserEndpoint;
import pro.learnup.api.ext.ApiTestExtension;
import pro.learnup.testdata.DbTestDataHelper;

import static org.assertj.core.api.Assertions.assertThat;

import static io.restassured.RestAssured.given;

@DisplayName("/api/user")
@ExtendWith(ApiTestExtension.class)
public class ApiUserTest {

    String token;
    String userName;
    String id;
    UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new ApiAuthRegisterEndpoint().registerNewUser(ApiAuthTest.successfulCreateUserRequests().findFirst().orElseThrow());
    }
    @Test
    @DisplayName("/api/user: 200: получение информации о юзере авторизованным пользователем")
    void successfulGetUserTest() {
        assertThat(new ApiUserEndpoint().getUser(userDto))
                .usingRecursiveComparison()
                .isEqualTo(userDto);
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