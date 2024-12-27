package api.account;

import static io.restassured.RestAssured.given;

import io.qameta.allure.Step;
import models.GetUserResponse;
import models.LoginResponse;

import static data.endpoints.AccountEndPoints.LOGIN;
import static data.endpoints.AccountEndPoints.USER;
import static specs.Request.requestSpec;
import static specs.Request.responseSpec;
import static data.UserData.getUsersAuthData;

public class Account {
    @Step("Log into the BookStore.")
    public static LoginResponse login() {
        return
            given().
                spec(requestSpec).
                body(getUsersAuthData()).
                when().
                post(LOGIN).
                then().
                spec(responseSpec).
                statusCode(200).
                extract().as(LoginResponse.class);
    }

    public static LoginResponse authUser = login();

    @Step("The user has no books in the cart (by API).")
    public GetUserResponse getUserEmptyBooksList(String userUuid) {
        return
            given().
                spec(requestSpec).
                header("Authorization", "Bearer " + authUser.getToken()).
                when().
                get(USER + userUuid).
                then().
                statusCode(200).
                extract().as(GetUserResponse.class);
    }
}