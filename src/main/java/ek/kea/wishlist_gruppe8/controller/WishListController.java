package ek.kea.wishlist_gruppe8.controller;

import ek.kea.wishlist_gruppe8.service.WishListService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


    @Controller
    public class WishListController {

        private final WishListService wishListService;

        public WishListController(WishListService wishListService) {
            this.wishListService = wishListService;
        }

        // Viser formularen
        @GetMapping("/wishlists/new")
        public String showCreateForm() {
            return "create-wishlist";
        }

        // Modtager formularen og gemmer
        @PostMapping("/wishlists")
        public String createWishList(@RequestParam String title,
                                     @RequestParam(required = false) String description) {
            wishListService.createWishList(title, description);
            return "redirect:/wishlists/new";
        }
    }