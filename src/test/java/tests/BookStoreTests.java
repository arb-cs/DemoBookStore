package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static api.account.Account.getUserEmptyBooksList;
import static api.store.BookStoreApi.deleteBooksFromTheCart;
import static api.store.BookStoreApi.addBookToTheCart;
import helpers.WithLogin;
import pages.Profile;
import static utils.TestData.ISBN;
import static utils.TestData.UUID;

public class BookStoreTests extends TestBase {

    @Test
    @WithLogin
    @Tag("ui_tests")
    void deleteBookFromTheCartUITest() {

        deleteBooksFromTheCart();
        addBookToTheCart(ISBN);

        Profile profile = new Profile();
        profile.openProfilePage();
        profile.clickOnAnItemToRemoveABookFromTheCart();
        profile.confirmDeletionOfABook();

        profile.checkThatTheBookWasDeleted(ISBN);
        getUserEmptyBooksList(UUID);
    }
}