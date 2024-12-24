package pages;

import api.store.BookStoreApi;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import models.BookModel;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Profile {

    private final SelenideElement deleteBook = $("#delete-record-undefined"),
            modalOkButton = $("#closeSmallModal-ok"),
            bookInTable = $(".ReactTable");

    @Step("Open profile page.")
    public Profile openProfilePage() {
        open("/profile");
        return this;
    }

    @Step("Delete a book.")
    public Profile clickOnAnItemToRemoveABookFromTheCart() {
        deleteBook.click();
        return this;
    }

    @Step("Click on OK window.")
    public Profile confirmDeletionOfABook() {
        modalOkButton.click();
        return this;
    }

    @Step("There is no book in the cart (by UI).")
    public Profile checkThatTheBookWasDeleted(String isbn) {
        BookModel book = BookStoreApi.getBooksList(isbn);
        bookInTable.shouldNotHave(text(book.getAuthor()))
                .shouldNotHave(text(book.getTitle()));
        return this;
    }

}
