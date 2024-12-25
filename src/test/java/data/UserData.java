package data;

import models.GenerateTokenRequest;

public class UserData {

    public static GenerateTokenRequest getUsersAuthData() {
        GenerateTokenRequest authData = new GenerateTokenRequest();

        String userName = System.getProperty("userName");
        String userPassword = System.getProperty("userPassword");

        if (userName == null) {
            throw new RuntimeException("You must provide a user name.");
        } else {
            authData.setUserName(userName);
        }

        if (userPassword == null) {
            throw new RuntimeException("You must provide a user password.");
        } else {
            authData.setPassword(userPassword);
        }

        return authData;
    }

}