package helpers;

import com.codeborne.selenide.Configuration;
import config.ProjectConfig;
import io.restassured.RestAssured;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class RunSettings {
    public static void configure() {

        String remote = System.getProperty("remote");
        String baseUrl = System.getProperty("baseUrl");
        String baseURI = System.getProperty("baseURI");
        boolean isRemote = remote != null || baseUrl != null || baseURI != null;

        if (isRemote) {
            Configuration.baseUrl = baseUrl;
            Configuration.remote = remote;
            RestAssured.baseURI = baseURI;
            Configuration.browser = System.getProperty("browser");
            Configuration.browserVersion = System.getProperty("browserVersion");

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", true
            ));

            Configuration.browserCapabilities = capabilities;
        } else {
            Configuration.baseUrl = ProjectConfig.config.baseUrl();
            RestAssured.baseURI = ProjectConfig.config.baseURI();
            Configuration.pageLoadStrategy = ProjectConfig.config.pageLoadStrategy();
            Configuration.browser = ProjectConfig.config.browser();
            Configuration.browserVersion = ProjectConfig.config.browserVersion();
            Configuration.browserSize = ProjectConfig.config.browserSize();
        }
    }
}