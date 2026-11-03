
package ek.kea.wishlist_gruppe8.service;

import ek.kea.wishlist_gruppe8.model.Wish;
import ek.kea.wishlist_gruppe8.model.WishList;
import ek.kea.wishlist_gruppe8.repository.WishListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {

    private final WishListRepository wishRepository;

    public WishListService(WishListRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    // UPDATE: updates a wish
    public Wish updateWish(int id, Wish updated) {
        return wishRepository.updateWish(id, updated);
    }

    // UPDATE: updates a wishlist
    public WishList updateWishList(int id, WishList updated) {
        return wishRepository.updateWishList(id, updated);
    }

    public List<Wish> getWishesByWishlistId(int wishlist_id){
        return wishListRepository.getWishesByWishlistId(wishlist_id);
    }

    public WishList getWishListById(int id){
        return wishListRepository.getWishListById(id);
    }
}

