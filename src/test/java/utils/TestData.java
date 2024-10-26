package utils;

import models.GenerateTokenRequest;

public class TestData {

    public static GenerateTokenRequest getUsersAuthData() {
        GenerateTokenRequest authData = new GenerateTokenRequest();
        authData.setUserName("JonSnow");
        authData.setPassword("TheNorthRemembers!?%042");

        return authData;
    }

}