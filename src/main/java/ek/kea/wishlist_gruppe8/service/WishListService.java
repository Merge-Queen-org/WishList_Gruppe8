
package ek.kea.wishlist_gruppe8.service;

import ek.kea.wishlist_gruppe8.model.Wish;
import ek.kea.wishlist_gruppe8.model.WishList;
import ek.kea.wishlist_gruppe8.repository.WishListRepository;
import org.springframework.stereotype.Service;

@Service
public class WishListService {

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
}

