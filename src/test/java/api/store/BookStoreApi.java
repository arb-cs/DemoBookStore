package api.store;

import models.AddBookToCart;
import models.Isbn;
import tests.TestBase;
import static api.authorization.Account.setCookie;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static api.endpoints.BookStoreEndPoints.ADD_OR_DELETE_BOOKS;
import static specs.Request.requestSpec;
import static specs.Request.responseSpec;
import java.util.List;

public class BookStoreApi extends TestBase {

    static String token = setCookie().getToken();
    static String userId = setCookie().getUserId();

    public static void addBookToTheCart(String isbn) {
        AddBookToCart bookBody = new AddBookToCart();
        Isbn bookIsbn = new Isbn();
        bookBody.setUserId(userId);
        bookIsbn.setIsbn(isbn);
        bookBody.setCollectionOfIsbns(List.of(bookIsbn));
        given()
                .spec(requestSpec)
                .contentType(JSON)
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
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .header("Authorization", "Bearer " + token)
                .queryParams("UserId", userId)
                .when()
                .delete(ADD_OR_DELETE_BOOKS)
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

}