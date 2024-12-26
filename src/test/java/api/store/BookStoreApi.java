package api.store;

import java.util.List;
import static io.restassured.RestAssured.given;
import io.qameta.allure.Step;
import static data.endpoints.BookStoreEndPoints.*;
import models.AddBookToCart;
import models.Isbn;
import models.BookModel;
import static api.account.Account.authUser;
import static specs.Request.requestSpec;
import static specs.Request.responseSpec;

public class BookStoreApi {
    private static final String token = authUser.getToken();
    private static final String userId = authUser.getUserId();

    @Step("Add book to the cart.")
    public void addBookToTheCart(String isbn) {
        AddBookToCart bookBody = new AddBookToCart();
        Isbn bookIsbn = new Isbn();
        bookBody.setUserId(userId);
        bookIsbn.setIsbn(isbn);
        bookBody.setCollectionOfIsbns(List.of(bookIsbn));

        given().
            spec(requestSpec).
            header("Authorization", "Bearer " + token).
            body(bookBody).
        when().
            post(ADD_OR_DELETE_BOOKS).
        then().
            spec(responseSpec).
            statusCode(201);
    }

    @Step("Get books list.")
    public BookModel getBooksList(String isbn) {
        return
            given(requestSpec).
                queryParam("ISBN", isbn).
            when().
                get(BASE_URI).
            then().
                statusCode(200).
                extract().as(BookModel.class);
        }

    @Step("Delete books from the cart ((if there were being added earlier))")
    public void deleteBooksFromTheCart() {
            given().
                spec(requestSpec).
                header("Authorization", "Bearer " + token).
                queryParams("UserId", userId).
            when().
                delete(ADD_OR_DELETE_BOOKS).
            then().
                spec(responseSpec).
                statusCode(204);
    }
}