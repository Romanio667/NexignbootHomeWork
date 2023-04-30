package pro.learnup.tests.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.learnup.api.dto.ItemDto;
import pro.learnup.api.dto.PhoneDto;
import pro.learnup.api.endpoints.ApiCartEndpoint;
import pro.learnup.extensions.UiTest;
import pro.learnup.pages.PhonesPage;
import pro.learnup.testdata.DbTestDataHelper;
import pro.learnup.testdata.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pro.learnup.steps.UiSteps.openPage;
import static pro.learnup.testdata.ApiTestDataHelper.createTestUser;

@UiTest
public class AddToCartTest {
    User user;
    PhoneDto phoneDto;

    @BeforeEach
    void setUp() {
        user = createTestUser();
        phoneDto = DbTestDataHelper.getAllPhones().get(0);
    }

    @Test
    void addToCartTest() {
        openPage(user, PhonesPage.class)
                .selectPhone(phoneDto.getInfo().getName())
                .checkPhoneName(phoneDto.getInfo().getName())
                .clickAddToCart()
                .checkSuccessfulPhoneAddedToCart();

        List<ItemDto> items = new ApiCartEndpoint().getCart(user).getItems();

        assertThat(items).hasSize(1);
        assertThat(items.get(0))
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(ItemDto.builder()
                        .product(phoneDto)
                        .quantity(1)
                        .build());
    }
}
