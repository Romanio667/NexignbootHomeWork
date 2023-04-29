package pro.learnup.api.endpoints;

import io.qameta.allure.Step;
import pro.learnup.api.dto.OrderRequestDto;
import pro.learnup.api.dto.UserDto;

import static io.restassured.RestAssured.given;

@Endpoint("/api/order")
public class ApiOrderEndpoint extends BaseEndpoint {

    @Step("{this.endpoint}: Оформление заказа")
    public void orderPhones(UserDto userDto, OrderRequestDto orderRequestDto) {
        given()
                .header(userDto.authHeader())
                .body(orderRequestDto)
                .post(getEndpoint())
                .then()
                .statusCode(200);
    }

}
