package pro.learnup.db;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.aeonbits.owner.ConfigFactory;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import pro.learnup.api.dto.PhoneDto;
import pro.learnup.config.TestConfig;

public class MongoConnector {
    private static MongoClient mongoClient;


    private static MongoClient getMongoClient() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(ConfigFactory.create(TestConfig.class).dbConnection());
        }
        return mongoClient;
    }

    public static MongoDatabase getDataBase() {
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        return getMongoClient().getDatabase(ConfigFactory.create(TestConfig.class).dbName()).withCodecRegistry(codecRegistry);
    }

    public static void main(String[] args) {
        System.out.println(getDataBase().getCollection("products", PhoneDto.class)
                .find(Filters.eq("info.name", "Huawei Mate 10 Pro"))
                .first());
    }
}
