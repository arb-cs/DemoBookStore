package helpers;

import com.codeborne.selenide.Configuration;
import config.ProjectConfig;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class DriverSettings {
    public static void configure() {
        Configuration.baseUrl = ProjectConfig.config.baseUrl();
        Configuration.remote = System.getProperty("remote");
        Configuration.pageLoadStrategy = ProjectConfig.config.pageLoadStrategy();
        Configuration.browserSize = ProjectConfig.config.browserSize();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
            "enableVNC", true,
            "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;
    }
}