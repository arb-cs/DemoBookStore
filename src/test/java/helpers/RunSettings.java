package helpers;

import com.codeborne.selenide.Configuration;
import config.BookStore;
import io.restassured.RestAssured;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class RunSettings {
    public static void configure() {
        if (BookStore.config.remoteUrl() != null) {
            Configuration.remote = BookStore.config.remoteUrl();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", true
            ));

            Configuration.browserCapabilities = capabilities;
        }
        Configuration.baseUrl = BookStore.config.baseUrl();
        Configuration.pageLoadStrategy = BookStore.config.pageLoadStrategy();
        RestAssured.baseURI = BookStore.config.baseURI();
        Configuration.browser = BookStore.config.browser();
        Configuration.browserVersion = BookStore.config.browserVersion();
        Configuration.browserSize = BookStore.config.browserSize();
    }
}