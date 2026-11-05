package ek.kea.wishlist_gruppe8;

import ek.kea.wishlist_gruppe8.controller.WishListController;
import ek.kea.wishlist_gruppe8.model.Wish;
import ek.kea.wishlist_gruppe8.model.WishList;
import ek.kea.wishlist_gruppe8.service.WishListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WishListController.class)
public class WishListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WishListService wishListService;

    @Test
    void getWishlistById() throws Exception {
        when(wishListService.getWishListById(1)).thenReturn(new WishList());

        mockMvc.perform(get("/wishlist/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("wishlist"))
                .andExpect(model().attributeExists("wishList"));
    }

    @Test
    void addNewWish() throws Exception {
        when(wishListService.getWishListById(2)).thenReturn(new WishList());

        mockMvc.perform(get("/wishlist/2/wish/new"))
                .andExpect(status().isOk())
                        .andExpect(view().name("wish-form"))
                        .andExpect(model().attributeExists("wishList"))
                        .andExpect(model().attributeExists("wish"));

    }
}
