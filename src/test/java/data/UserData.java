package data;

import config.ProjectConfig;
import models.GenerateTokenRequest;

public class UserData {
    public static GenerateTokenRequest getUsersAuthData() {
        GenerateTokenRequest authData = new GenerateTokenRequest();

        String name = System.getProperty("userName");
        String password = System.getProperty("userPassword");

        if (name == null) {
            authData.setUserName(ProjectConfig.config.name());
        } else {
            authData.setUserName(name);
        }

        if (password == null) {
            authData.setPassword(ProjectConfig.config.password());
        } else {
            authData.setPassword(password);
        }

        return authData;
    }
}