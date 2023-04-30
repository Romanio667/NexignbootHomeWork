package pro.learnup.api;

import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pro.learnup.api.dto.Order;
import pro.learnup.api.dto.OrderRequestDto;
import pro.learnup.api.dto.PhoneDto;
import pro.learnup.api.dto.UserDto;
import pro.learnup.api.endpoints.ApiAuthRegisterEndpoint;
import pro.learnup.api.endpoints.ApiCatalogEndpoint;
import pro.learnup.api.endpoints.ApiOrderEndpoint;
import pro.learnup.api.endpoints.ApiUserEndpoint;
import pro.learnup.api.ext.ApiTestExtension;
import pro.learnup.testdata.DbTestDataHelper;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("/api/order")
@ExtendWith({ApiTestExtension.class, AllureJunit5.class})
public class ApiOrderTest {
    UserDto userDto;
    PhoneDto phoneDto;

    @BeforeEach
    void setUp() {
        userDto = new ApiAuthRegisterEndpoint().registerNewUser(ApiAuthTest.successfulCreateUserRequests().findFirst().orElseThrow());
        phoneDto = new ApiCatalogEndpoint().getAllPhones().get(0);
    }

    @Test
    @DisplayName("/api/order: 200: успешное оформление заказа")
    void apiOrderSuccessfulTest() {
        Order expectedOrder = Order.builder()
                .dateCreated(LocalDateTime.now())
                .name(phoneDto.getInfo().getName())
                .price(phoneDto.getInfo().getPrice())
                .quantity(1)
                .build();

        new ApiOrderEndpoint().orderPhones(userDto, OrderRequestDto.builder()
                .order(expectedOrder)
                .build());

        assertThat(new ApiUserEndpoint().getUser(userDto).getOrders())
                .as("У юзера добавился один заказ")
                .containsExactlyInAnyOrder(expectedOrder);
    }
    @AfterEach
    void tearDown() {
        DbTestDataHelper.deleteUser(userDto);
    }
}
