package api.authorization;

import models.LoginResponse;
import tests.TestBase;
import org.openqa.selenium.Cookie;
import static api.endpoints.AccountEndPoints.LOGIN;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static specs.Request.requestSpec;
import static specs.Request.responseSpec;
import static utils.TestData.getUsersAuthData;

public class Account extends TestBase{

    public static LoginResponse login() {
        return given()
                .spec(requestSpec)
                .contentType(JSON)
                .body(getUsersAuthData())
                .when()
                .post(LOGIN)
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract().as(LoginResponse.class);
    }

    public static LoginResponse setCookie() {
        LoginResponse authUser = login();
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", authUser.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", authUser.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token", authUser.getToken()));

        return authUser;
    }

}