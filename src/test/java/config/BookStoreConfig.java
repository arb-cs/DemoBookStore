package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
    "system:properties",
    "classpath:config/local.properties",
    "classpath:config/remote.properties",
    "classpath:config/user.properties"
})
public interface BookStoreConfig extends Config {
    @Key("webdriver.baseUrl")
    String baseUrl();

    @Key("webdriver.pageLoadStrategy")
    String pageLoadStrategy();

    @Key("webdriver.browserSize")
    String browserSize();

    @Key("api.baseURI")
    String baseURI();

    @Key("webdriver.browser")
    String browser();

    @Key("webriver.browserVersion")
    String browserVersion();

    @Key("name")
    String name();

    @Key("password")
    String password();
}