package models;

import lombok.Data;

@Data
public class GenerateTokenRequest {
    private String userName;
    private String password;
}