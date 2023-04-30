package pro.learnup.config;

import org.aeonbits.owner.Config;


@Config.Sources("file:src/test/resources/localconfig.properties")
public interface TestConfig extends Config {
    String baseUrl();
    String baseUri();
    String dbConnection();
    String dbName();
}
