package ek.kea.wishlist_gruppe8.controller;


import ek.kea.wishlist_gruppe8.model.Wish;
import ek.kea.wishlist_gruppe8.model.WishList;
import ek.kea.wishlist_gruppe8.service.WishListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/wishlist/{id}")
    public String getWishlistById (@PathVariable int id, Model model){
        WishList wishList = wishListService.getWishListById(id);
        if (wishList == null) return "redirect:/";
        model.addAttribute("wishList", wishList);
        return "wishlist";
    }
    @GetMapping("/wishlist/{id}/wish/new")
    public String addNewWish(@PathVariable int id, Model model){
        WishList wishList = wishListService.getWishListById(id);
        if (wishList == null) return "redirect:/"; //if the wishlist id doesn’t exist, send the user to the front page instead of crashing
       model.addAttribute("wishList",wishList);
       model.addAttribute("wish", new Wish()); //gives the form an empty wish to fill in
       return "wish-form";
    }

    @PostMapping("/wishlist/{id}/wishes")
    public String saveWishes (@PathVariable int id, @ModelAttribute Wish wish){
        wishListService.saveNewWish(id,wish);
        return "redirect:/wishlist/" + id;
    }
    @GetMapping("/wishlist/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        WishList wishList = wishListService.getWishListById(id);
        if (wishList == null) return "redirect:/";
        model.addAttribute("wishList", wishList);
        return "edit-wishlist";
    }
    @PostMapping("/wishList/edit/{wishList_id}")
    public String editWishList(@PathVariable int wishList_id, @ModelAttribute WishList wishList) {
        wishListService.editWishList(wishList_id, wishList);
        return "redirect:/wishList";
    }

    @PostMapping("/wish/edit/{wish_id}")
    public String editWish(@PathVariable int wish_id,@ModelAttribute Wish wish) {
        wishListService.editWish(wish_id, wish);
        return "redirect:/wish";
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

    // Modtager formularen og gemmer
    @PostMapping("/wishlists")
    public String createWishList(@RequestParam String title,
                                 @RequestParam(required = false) String description) {
        wishListService.createWishList(title, description);
        return "redirect:/wishlists/new";
    }
}
