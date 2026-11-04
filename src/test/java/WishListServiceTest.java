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
}
