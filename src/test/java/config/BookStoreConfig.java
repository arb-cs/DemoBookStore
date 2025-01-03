package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
    "classpath:config/${env}.properties",
    "classpath:config/remote.properties"
})
public interface BookStoreConfig extends Config {
    @Key("webdriver.baseUrl")
    String baseUrl();

    @Key("webdriver.remoteUrl")
    String remoteUrl();

    @Key("webdriver.pageLoadStrategy")
    String pageLoadStrategy();

    @Key("webdriver.browser")
    String browser();

    @Key("webdriver.browserSize")
    String browserSize();

    @Key("api.baseURI")
    String baseURI();

    @Key("webdriver.browserVersion")
    String browserVersion();
}