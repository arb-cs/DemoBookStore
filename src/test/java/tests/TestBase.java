package tests;

import helpers.AllureAttachments;
import org.junit.jupiter.api.*;
import io.qameta.allure.selenide.AllureSelenide;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.restassured.RestAssured;
import java.util.Map;

public class TestBase {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://demoqa.com";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;

    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    public void afterEach() {
        AllureAttachments.screenshotAs("Last screenshot");
        AllureAttachments.addVideo();
        AllureAttachments.pageSource();
        AllureAttachments.browserConsoleLogs();

        Selenide.closeWebDriver();
    }

}