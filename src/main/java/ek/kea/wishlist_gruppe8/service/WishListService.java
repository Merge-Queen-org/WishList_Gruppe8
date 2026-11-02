
package ek.kea.wishlist_gruppe8.service;

import ek.kea.wishlist_gruppe8.model.Wish;
import ek.kea.wishlist_gruppe8.model.WishList;
import ek.kea.wishlist_gruppe8.repository.WishListRepository;
import org.springframework.stereotype.Service;

@Service
public class WishListService {

    private final WishListRepository wishListRepository;

    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    // Tjekker titel og gemmer
    public void createWishList(String title, String description) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Titel må ikke være tom");
        }

        wishListRepository.save(new WishList(title, description));
    }

    // UPDATE: updates a wish
    public Wish editWish(int id, Wish updated) {
        return wishListRepository.editWish(id, updated);
    }

    // UPDATE: updates a wishlist
    public WishList editWishList(int id, WishList updated) {
        return wishListRepository.editWishList(id, updated);
    }
}
