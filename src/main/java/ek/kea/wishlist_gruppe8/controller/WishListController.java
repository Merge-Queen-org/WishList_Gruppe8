package ek.kea.wishlist_gruppe8.controller;


import ek.kea.wishlist_gruppe8.model.Wish;
import ek.kea.wishlist_gruppe8.model.WishList;
import ek.kea.wishlist_gruppe8.service.WishListService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WishListController {
    private final WishListService wishListService;

    public WishListController(WishListService wishListService) { // constructor injection
        this.wishListService = wishListService;
    }

    @PostMapping("/wishList/edit")
    public String update(@ModelAttribute WishList wishList) {
        wishListService.editWishList(wishList.getWishList_id(), wishList);
        return "redirect:/wishList";
    }

    @PostMapping("/wish/edit")
    public String update(@ModelAttribute Wish wish) {
        wishListService.editWish(wish.getWish_id(), wish);
        return "redirect:/wish";
    }
}
