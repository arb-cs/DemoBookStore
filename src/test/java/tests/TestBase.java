package tests;

import org.junit.jupiter.api.*;
import io.qameta.allure.selenide.AllureSelenide;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.RunSettings;
import helpers.AllureAttachments;

public class TestBase {

    @BeforeAll
    public static void setUp() {
        RunSettings.configure();
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