package models;

import lombok.Data;
import java.util.ArrayList;
import java.util.Map;

@Data
public class GetUserResponse {
    private String userId;
    private String username;
    private ArrayList<Map<String,?>> books;
}