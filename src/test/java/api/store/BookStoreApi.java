package api.store;

import models.AddBookToCart;
import models.Isbn;
import tests.TestBase;
import static api.account.Account.authUser;
import static io.restassured.RestAssured.given;
import static api.endpoints.BookStoreEndPoints.ADD_OR_DELETE_BOOKS;
import static specs.Request.requestSpec;
import static specs.Request.responseSpec;
import java.util.List;

public class BookStoreApi extends TestBase {

    private static final String token = authUser.getToken();
    private static final String userId = authUser.getUserId();

    public static void addBookToTheCart(String isbn) {
        AddBookToCart bookBody = new AddBookToCart();
        Isbn bookIsbn = new Isbn();
        bookBody.setUserId(userId);
        bookIsbn.setIsbn(isbn);
        bookBody.setCollectionOfIsbns(List.of(bookIsbn));
        given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .body(bookBody)
                .when()
                .post(ADD_OR_DELETE_BOOKS)
                .then()
                .spec(responseSpec)
                .statusCode(201);
    }

    public static void deleteBooksFromTheCart() {
        given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .queryParams("UserId", userId)
                .when()
                .delete(ADD_OR_DELETE_BOOKS)
                .then()
                .spec(responseSpec)
                .statusCode(204);
    }

}