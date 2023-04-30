package pro.learnup.extensions;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import pro.learnup.config.TestConfig;

public class ApiTestExtension implements BeforeAllCallback {
    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        RestAssured.baseURI = ConfigFactory.create(TestConfig.class).baseUri();
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON).build();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.filters(new AllureRestAssured());
        RestAssured.config = RestAssured.config().objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.JACKSON_2));
    }
}
