package pro.learnup.apiPetShop.ext;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ApiTestExtensionPet implements BeforeAllCallback {

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON).build();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.filters(new AllureRestAssured());
    }
}
