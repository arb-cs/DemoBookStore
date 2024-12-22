package api.account;

import models.GetUserResponse;
import models.LoginResponse;
import org.openqa.selenium.Cookie;
import java.util.Collections;
import static api.endpoints.AccountEndPoints.LOGIN;
import static api.endpoints.AccountEndPoints.USER;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static specs.Request.requestSpec;
import static specs.Request.responseSpec;
import static utils.TestData.getUsersAuthData;

public class Account {

    public static LoginResponse login() {
        return  given()
                .spec(requestSpec)
                .body(getUsersAuthData())
                .when()
                .post(LOGIN)
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract().as(LoginResponse.class);
    }

    public static LoginResponse authUser = login();

    public static void setCookie(LoginResponse authUser) {
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", authUser.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", authUser.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token", authUser.getToken()));
    }

    public static void getUserEmptyBooksList(String UUID) {
       GetUserResponse response = given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + authUser.getToken())
                .when()
                .get(USER + UUID)
                .then()
                .statusCode(200)
               .extract().as(GetUserResponse.class);

        assertThat(response.getBooks(), equalTo(Collections.emptyList()));
    }
}