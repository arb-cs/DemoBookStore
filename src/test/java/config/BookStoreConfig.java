package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
    "system:properties",
    "classpath:config/local.properties",
    "classpath:config/remote.properties"
})
public interface BookStoreConfig extends Config {
    // remote configuration
    @Key("webdriver.remote.baseUrl")
    String baseUrl();
    @Key("webdriver.remoteUrl")
    String remoteUrl();
    @Key("webdriver.remote.pageLoadStrategy")
    String pageLoadStrategy();
    @Key("webdriver.remote.browser")
    String browser();
    @Key("webdriver.remote.browserSize")
    String browserSize();
    @Key("api.remote.baseURI")
    String baseURI();
    @Key("webdriver.remote.browserVersion")
    String browserVersion();

    // local configuration
    @Key("webdriver.local.localBaseUrl")
    String localBaseUrl();
    @Key("webdriver.local.pageLoadStrategy")
    String localPageLoadStrategy();
    @Key("webdriver.local.browser")
    String localBrowser();
    @Key("webdriver.local.browserSize")
    String localBrowserSize();
    @Key("api.local.baseURI")
    String localBaseURI();
    @Key("webriver.local.browserVersion")
    String localBrowserVersion();
}