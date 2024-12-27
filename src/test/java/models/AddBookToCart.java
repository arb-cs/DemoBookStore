package models;

import lombok.Data;

import java.util.List;

@Data
public class AddBookToCart {
    private String userId;
    private List<Isbn> collectionOfIsbns;
}