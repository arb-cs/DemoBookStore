package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import api.account.Account;
import api.store.BookStoreApi;
import models.GetUserResponse;
import helpers.WithLogin;
import pages.Profile;
import static data.TestData.ISBN;

public class BookStoreTests extends TestBase {

    @Test
    @WithLogin
    @Tag("ui_tests")
    void deleteBookFromTheCartUITest() {
        BookStoreApi bookStoreApi = new BookStoreApi();
        bookStoreApi.deleteBooksFromTheCart();
        bookStoreApi.addBookToTheCart(ISBN);

        Profile profile = new Profile();
        profile.openProfilePage();
        profile.clickOnAnItemToRemoveABookFromTheCart();
        profile.confirmDeletionOfABook();
        profile.checkThatTheBookWasDeleted(ISBN);

        String userUuid = Account.authUser.getUserId();
        Account account = new Account();
        GetUserResponse response = account.getUserEmptyBooksList(userUuid);
        assertThat(response.getBooks(), equalTo(Collections.emptyList()));
    }
}