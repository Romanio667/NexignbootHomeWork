package pro.learnup.apiPetShop;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pro.learnup.apiPetShop.ext.ApiTestExtensionPet;

import static io.restassured.RestAssured.given;

@DisplayName("/user")
@ExtendWith(ApiTestExtensionPet.class)
public class ApiAuthTestPet {
    @Test
    @DisplayName("/user: 200: создание нового пользователя")
    void createNewUser() {
        given()
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"username\": \"admin\",\n" +
                        "  \"firstName\": \"admin\",\n" +
                        "  \"lastName\": \"admin\",\n" +
                        "  \"email\": \"lalala@ngs.ru\",\n" +
                        "  \"password\": \"admin\",\n" +
                        "  \"phone\": \"12391231293\",\n" +
                        "  \"userStatus\": 0\n" +
                        "}")
                .post("/user")
                .then()
                .statusCode(200)
                .body("code", Matchers.equalTo(200))
                .body("type", Matchers.equalTo("unknown"));
    }
    @Test
    @DisplayName("/login: 200: авторизация с существующим пользователем")
    void authPositiveUser() {
        given()
                .param("username","admin")
                .param("password","admin")
                .get("/user/login")
                .then()
                .statusCode(200)
                .body("code", Matchers.equalTo(200))
                .body("type", Matchers.equalTo("unknown"));
    }
    @Test
    @DisplayName("/login: 200: авторизация с пустыми полями")
    void authNegativeUser() {
        given()
                .param("username","")
                .param("password","")
                .get("/user/login")
                .then()
                .statusCode(400);
    }
    @Test
    @DisplayName("/user/logout: 200: получение логов текущего пользователя")
    void getUserLog() {
        given()
                .get("/user/logout")
                .then()
                .statusCode(200)
                .body("code", Matchers.equalTo(200))
                .body("message", Matchers.equalTo("ok"));
    }

    @Test
    @DisplayName("/user/{username}: 200: обновление данных о пользователе")
    void putUser() {
        given()
                .pathParam("username","admin")
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"username\": \"admin\",\n" +
                        "  \"firstName\": \"admin\",\n" +
                        "  \"lastName\": \"admin\",\n" +
                        "  \"email\": \"test1@ngs.ru\",\n" +
                        "  \"password\": \"12345\",\n" +
                        "  \"phone\": \"42384923\",\n" +
                        "  \"userStatus\": 0\n" +
                        "}")
                .put("/user/{username}")
                .then()
                .statusCode(200)
                .body("code", Matchers.equalTo(200));
    }

    @Test
    @DisplayName("/user/{username}: 400: ввод пустых полей и невалидных данных")
    void putNegativeUser() {
        given()
                .pathParam("username","admin")
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"username\": \"\",\n" +
                        "  \"firstName\": \"\",\n" +
                        "  \"lastName\": \"\",\n" +
                        "  \"email\": \"222222222\",\n" +
                        "  \"password\": \"цццццццццц\",\n" +
                        "  \"phone\": \"wwwwww\",\n" +
                        "  \"userStatus\": 0\n" +
                        "}")
                .put("/user/{username}")
                .then()
                .statusCode(400)
                .body("code", Matchers.equalTo(400));
    }

    @Test
    @DisplayName("/user/{username}: 200: удаление существующего пользователя")
    void deleteUser() {
        given()
                .pathParam("username","admin")
                .delete("/user/{username}")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("/user/{username}: 404: удаление несуществующего пользователя")
    void deleteNegativeUser() {
        given()
                .pathParam("username","g;]]re'grtg")
                .delete("/user/{username}")
                .then()
                .statusCode(404);
    }
}
