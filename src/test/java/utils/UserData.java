package utils;

import models.GenerateTokenRequest;

public class UserData {

    public static GenerateTokenRequest getUsersAuthData() {
        GenerateTokenRequest authData = new GenerateTokenRequest();

        if (System.getProperty("userName") == null) {
            throw new RuntimeException("You must provide user name.");
        } else {
            authData.setUserName(System.getProperty("userName"));
        }

        if (System.getProperty("userPassword") == null) {
            throw new RuntimeException("You must provide user name.");
        } else {
            authData.setPassword(System.getProperty("userPassword"));
        }

        return authData;
    }

}