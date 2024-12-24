package api.store;

import io.qameta.allure.Step;
import models.AddBookToCart;
import models.Isbn;
import models.BookModel;
import static api.account.Account.authUser;
import static api.endpoints.BookStoreEndPoints.GET_BOOKS;
import static io.restassured.RestAssured.given;
import static api.endpoints.BookStoreEndPoints.ADD_OR_DELETE_BOOKS;
import static specs.Request.requestSpec;
import static specs.Request.responseSpec;
import java.util.List;

public class BookStoreApi {

    private static final String token = authUser.getToken();
    private static final String userId = authUser.getUserId();

    @Step("Add book to the cart.")
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

    @Step("Get books list.")
    public static BookModel getBooksList(String isbn) {
        return given(requestSpec)
                .get(GET_BOOKS + isbn)
                .then()
                .statusCode(200)
                .extract().as(BookModel.class);
        }

    @Step("Delete books from the cart ((if there were being added earlier))")
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