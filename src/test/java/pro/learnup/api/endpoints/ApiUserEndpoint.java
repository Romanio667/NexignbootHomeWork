package pro.learnup.api.endpoints;

import io.qameta.allure.Step;
import pro.learnup.api.dto.UserDto;

import static io.restassured.RestAssured.given;

@Endpoint("/api/user")
public class ApiUserEndpoint extends BaseEndpoint {

    @Step("{this.endpoint}: Получении информации об юзера {userDto.username}")
    public UserDto getUser(UserDto userDto) {
        return given()
                .header(userDto.authHeader())
                .get(getEndpoint())
                .then()
                .statusCode(200)
                .extract()
                .as(UserDto.class);
    }

}
