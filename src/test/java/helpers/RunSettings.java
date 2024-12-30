package helpers;

import com.codeborne.selenide.Configuration;
import config.BookStore;
import io.restassured.RestAssured;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class RunSettings {
    public static void configure() {

        String remote = System.getProperty("remote");

        if (remote != null) {
            Configuration.baseUrl = BookStore.config.baseUrl();
            Configuration.pageLoadStrategy = BookStore.config.pageLoadStrategy();
            Configuration.remote = BookStore.config.remoteUrl();
            RestAssured.baseURI = BookStore.config.baseURI();
            Configuration.browser = BookStore.config.browser();
            Configuration.browserVersion = BookStore.config.browserVersion();
            Configuration.browserSize = BookStore.config.browserSize();

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", true
            ));

            Configuration.browserCapabilities = capabilities;
        } else {
            Configuration.baseUrl = BookStore.config.localBaseUrl();
            RestAssured.baseURI = BookStore.config.localBaseURI();
            Configuration.browser = BookStore.config.localBrowser();
            Configuration.browserVersion = BookStore.config.localBrowserVersion();
            Configuration.browserSize = BookStore.config.localBrowserSize();
        }
    }
}