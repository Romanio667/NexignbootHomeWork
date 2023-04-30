package pro.learnup.testdata;

import com.mongodb.client.model.Filters;
import io.qameta.allure.Step;
import org.bson.types.ObjectId;
import pro.learnup.api.dto.PhoneDto;
import pro.learnup.api.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

import static pro.learnup.db.MongoConnector.getDataBase;

public class DbTestDataHelper {
    @Step("Получить в бд все телефоны")
    public static List<PhoneDto> getAllPhones() {
        List<PhoneDto> phoneDtoList = new ArrayList<>();
        return getDataBase().getCollection("products", PhoneDto.class).find().into(phoneDtoList);
    }

    public static void deleteUser(UserDto userDto) {
        deleteUser(userDto.getId());
    }
    @Step("Удалить в бд юзера {id}")
    public static void deleteUser(ObjectId id) {
        getDataBase().getCollection("users").deleteOne(Filters.eq("_id", id));
    }
}
