package pro.learnup.api;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pro.learnup.api.ext.ApiTestExtension;

import static io.restassured.RestAssured.given;

@DisplayName("/api/catalog")
@ExtendWith(ApiTestExtension.class)
public class ApiCatalogTest {

    @Test
    @DisplayName("/api/catalog: 200, получение телефонов без авторизации")
    void getCatalogTest() {
        given()
                .get("/api/catalog")
                .then()
                .statusCode(200)
                .body("info.name", Matchers.hasItems("Apple iPhone 8 Plus",
                        "Apple iPhone X", "Huawei Mate 10 Pro"));
    }


}
