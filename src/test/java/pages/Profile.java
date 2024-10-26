package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class Profile {

    private final SelenideElement deleteBook = $("#delete-record-undefined"),
            modalOkButton = $("#closeSmallModal-ok");

    public Profile clickOnAnItemToRemoveABookFromTheCart() {
        deleteBook.click();
        return this;
    }

    public Profile confirmDeletionOfABook() {
        modalOkButton.click();
        return this;
    }

}
