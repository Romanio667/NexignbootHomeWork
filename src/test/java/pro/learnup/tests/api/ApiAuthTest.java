package pro.learnup.tests.api;

import com.github.javafaker.Faker;
import io.qameta.allure.junit5.AllureJunit5;
import org.assertj.core.api.SoftAssertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pro.learnup.api.dto.UserDto;
import pro.learnup.api.endpoints.ApiAuthRegisterEndpoint;
import pro.learnup.extensions.ApiTest;
import pro.learnup.extensions.ApiTestExtension;
import pro.learnup.testdata.ApiTestDataHelper;
import pro.learnup.testdata.DbTestDataHelper;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("/api/auth")
@ApiTest
public class ApiAuthTest {

    static Faker faker = new Faker();
    UserDto userDto;
    public static Stream<UserDto> successfulCreateUserRequests() {

        return Stream.of(UserDto.builder()
                .username(faker.name().fullName())
                .password(faker.internet().password())
                .build(),
                UserDto.builder()
                        .address(faker.address().fullAddress())
                        .phone(faker.phoneNumber().phoneNumber())
                        .email(faker.internet().emailAddress())
                        .username(faker.name().fullName())
                        .password(faker.internet().password())
                        .build()
                );
    }

    @ParameterizedTest
    @DisplayName("/api/auth/register: 201: успешное создание юзера")
    @MethodSource("successfulCreateUserRequests")
    void createUserTest(UserDto userDto) {
        this.userDto = new ApiAuthRegisterEndpoint().registerNewUser(userDto);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(this.userDto)
                        .as("Созданный юзер должен быть равен ожидаемому")
                        .usingRecursiveComparison()
                        .ignoringFields("id", "orders", "password", "token")
                        .isEqualTo(this.userDto);
        softAssertions.assertThat(this.userDto.getId().toString()).isNotEmpty();
        softAssertions.assertThat(this.userDto.getPassword()).isNotEmpty();
        softAssertions.assertThat(this.userDto.getToken()).isNotEmpty();
        softAssertions.assertThat(this.userDto.getToken()).isEmpty();
        softAssertions.assertAll();
    }



    public static Stream<UserDto> failedCreateUserRequests() {
        return Stream.of(UserDto.builder()
                        .password(faker.internet().password())
                        .build(),
                UserDto.builder()
                        .username(faker.name().fullName())
                        .build()
        );
    }

    @ParameterizedTest
    @DisplayName("/api/auth/register: 400: неуспешное создание юзера")
    @MethodSource("failedCreateUserRequests")
    void failedCreateUser400Test(UserDto userDto) {
       given()
                .body(userDto)
                .post(new ApiAuthRegisterEndpoint().getEndpoint())
                .then()
                .statusCode(400);
    }
    @Test
    @DisplayName("/api/auth/register: 409: создание существующего пользователя")
    void failedCreateUser409Test() {
        userDto = ApiTestDataHelper.createTestUserDto();

        given()
                .body(userDto)
                .post(new ApiAuthRegisterEndpoint().getEndpoint())
                .then()
                .statusCode(409)
                .body("message", Matchers.equalTo("User already exists"));
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

    @AfterEach
    void tearDown() {
        DbTestDataHelper.deleteUser(userDto);
    }
}
