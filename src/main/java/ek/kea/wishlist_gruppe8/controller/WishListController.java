package ek.kea.wishlist_gruppe8.controller;


import ek.kea.wishlist_gruppe8.model.Wish;
import ek.kea.wishlist_gruppe8.model.WishList;
import ek.kea.wishlist_gruppe8.service.WishListService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WishListController {

    private final WishListService wishListService;

    public WishListController(WishListService wishListService) { // constructor injection
        this.wishListService = wishListService;
    }

    @GetMapping("/")
    public String welcome() {
        return "index";
    }

    @PostMapping("/wishList/update")
    public String update(@ModelAttribute WishList wishList) {
        wishListService.updateWishList(wishList.getWishList_id(), wishList);
        return "redirect:/attractions";
    }

    @PostMapping("/wish/update")
    public String update(@ModelAttribute Wish wish) {
        wishListService.updateWish(wish.getWish_id(), wish);
        return "redirect:/attractions";
    }
    @PostMapping("/wish/delete/{wish_id}")
    public String deleteWish(@PathVariable int wish_id) {
        wishListService.deleteWish(wish_id);
        return "redirect:/wishlist";
    }
//@PathVariable and @ModelAttribute determine how Spring receives data.
@PostMapping("/wishlist/delete")
public String deleteWishLists(@RequestParam List<Integer> wishListIds) {

    for (int id : wishListIds) {
        wishListService.deleteWishList(id);
    }

    return "redirect:/wishlist";
}
}


