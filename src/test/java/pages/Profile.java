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
    public void openProfilePage() {
        open("/profile");
    }

    @Step("Delete a book.")
    public void clickOnAnItemToRemoveABookFromTheCart() {
        this.deleteBook.click();
    }

    @Step("Click on OK window.")
    public void confirmDeletionOfABook() {
        this.modalOkButton.click();
    }

    @Step("There is no book in the cart (by UI).")
    public void checkThatTheBookWasDeleted(String isbn) {
        BookStoreApi bookStoreApi = new BookStoreApi();
        BookModel book = bookStoreApi.getBooksList(isbn);
        this.bookInTable.shouldNotHave(text(book.getAuthor()))
                .shouldNotHave(text(book.getTitle()));
    }
}