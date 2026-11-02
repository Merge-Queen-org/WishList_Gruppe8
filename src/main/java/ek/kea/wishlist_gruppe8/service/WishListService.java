
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

    // Tjekker titel, laver et unikt share-token og gemmer
    public void createWishList(String title, String description) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Titel må ikke være tom");
        }

        wishListRepository.save(new WishList(title, description));
    }
}

    private final WishListRepository wishRepository;

    public WishListService(WishListRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    // UPDATE: updates a wish
    public Wish editWish(int id, Wish updated) {
        return wishRepository.editWish(id, updated);
    }

    // UPDATE: updates a wishlist
    public WishList editWishList(int id, WishList updated) {
        return wishRepository.editWishList(id, updated);
    }

    // DELETE a wish
    //The service receives the request from the controller and calls the repository.
    public boolean deleteWish(int wish_id ){

        return wishRepository.deleteWish(wish_id);
    }

    // DELETE a wishlist
    public boolean deleteWishList(int wishList_id) {
        return wishRepository.deleteWishList(wishList_id);
    }
}

