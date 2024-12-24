package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/project.properties"
})
public interface BookStoreConfig extends Config {
    @Key("webdriver.baseUrl")
    String baseUrl();

    @Config.Key("webdriver.pageLoadStrategy")
    String pageLoadStrategy();

    @Config.Key("webdriver.browserSize")
    String browserSize();

    @Key("api.baseURI")
    String baseURI();
}