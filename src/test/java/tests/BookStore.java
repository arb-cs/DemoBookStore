package tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import helpers.WithLogin;
import static api.store.BookStoreApi.deleteBooksFromTheCart;
import static api.store.BookStoreApi.addBookToTheCart;
import pages.Profile;

public class BookStore extends TestBase {

    @Test
    @WithLogin
    void deleteBookFromTheCartUITest() {
        step("Preconditions: delete books from the cart(if there were being added earlier), add book to the cart", () -> {
            deleteBooksFromTheCart();
            addBookToTheCart("9781449325862");
        });

        step("Open the profile page.", () -> {
            open("/profile");
        });

        Profile profile = new Profile();

        step("Delete a book.", () -> {
            profile.clickOnAnItemToRemoveABookFromTheCart();
            profile.confirmDeletionOfABook();
        });

        Selenide.refresh();
    }

}