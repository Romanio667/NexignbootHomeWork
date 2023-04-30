package pro.learnup.testdata;

import com.github.javafaker.Faker;
import pro.learnup.api.dto.UserDto;
import pro.learnup.api.endpoints.ApiAuthRegisterEndpoint;

public class ApiTestDataHelper {
    static Faker faker = new Faker();

    public static UserDto createTestUserDto() {
        return new ApiAuthRegisterEndpoint().registerNewUser(
                UserDto.builder()
                        .address(faker.address().fullAddress())
                        .phone(faker.phoneNumber().phoneNumber())
                        .email(faker.internet().emailAddress())
                        .username(faker.name().fullName())
                        .password(faker.internet().password())
                        .build());
    }


    public static User createTestUser() {
        User user = User.builder().login(faker.name().fullName())
                .password(faker.internet().password())
                .build();
        UserDto userDto =  new ApiAuthRegisterEndpoint().registerNewUser(
                UserDto.builder()
                        .address(faker.address().fullAddress())
                        .phone(faker.phoneNumber().phoneNumber())
                        .email(faker.internet().emailAddress())
                        .username(user.getLogin())
                        .password(user.getPassword())
                        .build());
        user.setToken(userDto.getToken());
        user.setId(userDto.getId());
        return user;

    }
}
