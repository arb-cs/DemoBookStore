package helpers;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static api.account.Account.authUser;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) {
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", authUser.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", authUser.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token", authUser.getToken()));
    }
}