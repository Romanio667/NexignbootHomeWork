package pro.learnup.api.endpoints;

import io.qameta.allure.Step;
import pro.learnup.api.dto.CartDto;
import pro.learnup.testdata.User;

import static io.restassured.RestAssured.given;

@Endpoint("/api/cart")
public class ApiCartEndpoint extends BaseEndpoint {

    @Step("{this.endpoint}: Получение корзины")
    public CartDto getCart(User user) {
        return given()
                .header(user.getAuthHeader())
                .get(getEndpoint())
                .then()
                .statusCode(200)
                .extract()
                .as(CartDto.class);
    }

}
