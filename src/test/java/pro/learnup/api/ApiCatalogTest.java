package pro.learnup.api;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pro.learnup.api.dto.PhoneDto;

import pro.learnup.api.endpoints.ApiCatalogEndpoint;
import pro.learnup.api.ext.ApiTestExtension;
import pro.learnup.testdata.DbTestDataHelper;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("/api/catalog")
@ExtendWith(ApiTestExtension.class)
public class ApiCatalogTest {
    List<PhoneDto> phoneDtoList;
    @BeforeEach
    void setUp() {
        phoneDtoList = DbTestDataHelper.getAllPhones();
    }

    @Test
    @DisplayName("/api/catalog: 200, получение телефонов без авторизации")
    void getCatalogTest() {
                assertThat(new ApiCatalogEndpoint().getAllPhones())
                        .containsExactlyInAnyOrderElementsOf(phoneDtoList);
    }
}
