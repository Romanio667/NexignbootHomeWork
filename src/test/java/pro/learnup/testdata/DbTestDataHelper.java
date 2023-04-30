package pro.learnup.testdata;

import com.mongodb.client.model.Filters;
import pro.learnup.api.dto.PhoneDto;
import pro.learnup.api.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

import static pro.learnup.db.MongoConnector.getDataBase;

public class DbTestDataHelper {
    public static List<PhoneDto> getAllPhones() {
        List<PhoneDto> phoneDtoList = new ArrayList<>();
        return getDataBase().getCollection("products", PhoneDto.class).find().into(phoneDtoList);
    }

    public static void deleteUser(UserDto userDto) {
        getDataBase().getCollection("users").deleteOne(Filters.eq("_id", userDto.getId()));
    }
}
