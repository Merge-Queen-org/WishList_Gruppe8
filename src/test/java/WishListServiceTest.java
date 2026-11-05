import ek.kea.wishlist_gruppe8.model.Wish;
import ek.kea.wishlist_gruppe8.repository.WishListRepository;
import ek.kea.wishlist_gruppe8.repository.WishRepository;
import ek.kea.wishlist_gruppe8.service.WishListService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class WishListServiceTest {

    @Test
    void testSaveNewWish(){
            WishListRepository wishListRepository = mock(WishListRepository.class);
            WishRepository wishRepository = mock(WishRepository.class);
            WishListService wishListService = new WishListService(wishListRepository, wishRepository);

            Wish wish = new Wish("Bag","Red","https://link",new BigDecimal("998"));
            wishListService.saveNewWish(5, wish);

            assertEquals(5, wish.getWishlist_id());
            verify(wishRepository).saveNewWish(wish);
    }
//blank-name test is the better choice. It checks the only real logic in the method,
// the validation, and it also checks that nothing gets saved.

    @Test
    void saveNewWish_blankName (){
        WishListRepository wishListRepository = mock(WishListRepository.class); //fake WishListRepository, it has no database behind
        WishRepository wishRepository = mock(WishRepository.class);
        WishListService wishListService = new WishListService(wishListRepository,wishRepository); //Creates the real service you're testing and hands it the two fakes.

        Wish wish = new Wish("","description","link", new BigDecimal("10"));

        assertThrows(IllegalArgumentException.class,
                () -> wishListService.saveNewWish(5, wish));  //is a lambda, which means "run this code later". assertThrows runs it itself, so it can catch the exception.
        verify(wishRepository, never()).saveNewWish(any()); //this must have happened zero times with any argument

    }

    @Test
    void saveNewWish_rejectsJavascriptLink() {
        WishRepository wishRepository = mock(WishRepository.class);
        WishListService service = new WishListService(mock(WishListRepository.class), wishRepository);

        Wish wish = new Wish("Bag", "Red", "javascript:alert(1)", new BigDecimal("10"));

        assertThrows(IllegalArgumentException.class, () -> service.saveNewWish(5, wish));
        verify(wishRepository, never()).saveNewWish(any());
    }

    @Test
    void saveNewWish_rejectsNegativePrice() {
        WishRepository wishRepository = mock(WishRepository.class);
        WishListService service = new WishListService(mock(WishListRepository.class), wishRepository);

        Wish wish = new Wish("Bag", "Red", "https://link", new BigDecimal("-1"));

        assertThrows(IllegalArgumentException.class, () -> service.saveNewWish(5, wish));
    }

    @Test
    void editWish_keepsOriginalWishlistId() {
        WishListRepository wishListRepository = mock(WishListRepository.class);
        WishRepository wishRepository = mock(WishRepository.class);
        WishListService service = new WishListService(wishListRepository, wishRepository);
        when(wishRepository.getWishById(7)).thenReturn(new Wish(7, 3, "Old", "d", null, null));
        when(wishListRepository.editWish(eq(7), any())).thenAnswer(i -> i.getArgument(1));

        Wish tampered = new Wish("New", "d", "https://link", null);
        tampered.setWishlist_id(99); // comes from the form, must be ignored
        Wish saved = service.editWish(7, tampered);

        assertEquals(3, saved.getWishlist_id());
    }

    @Test
    void editWish_unknownWishReturnsNull() {
        WishListRepository wishListRepository = mock(WishListRepository.class);
        WishRepository wishRepository = mock(WishRepository.class);
        WishListService service = new WishListService(wishListRepository, wishRepository);

        assertEquals(null, service.editWish(42, new Wish("New", "d", null, null)));
        verify(wishListRepository, never()).editWish(anyInt(), any());
    }

    @Test
    void editWishList_blankNameThrows() {
        WishListRepository wishListRepository = mock(WishListRepository.class);
        WishListService service = new WishListService(wishListRepository, mock(WishRepository.class));

        assertThrows(IllegalArgumentException.class,
                () -> service.editWishList(1, new ek.kea.wishlist_gruppe8.model.WishList(" ", "d")));
        verify(wishListRepository, never()).editWishList(anyInt(), any());
    }
}
