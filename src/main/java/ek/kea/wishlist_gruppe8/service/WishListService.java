
package ek.kea.wishlist_gruppe8.service;

import ek.kea.wishlist_gruppe8.model.Wish;
import ek.kea.wishlist_gruppe8.model.WishList;
import ek.kea.wishlist_gruppe8.repository.WishListRepository;
import ek.kea.wishlist_gruppe8.repository.WishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {

    private final WishListRepository wishListRepository;
    private final WishRepository wishRepository;

    public WishListService(WishListRepository wishListRepository, WishRepository wishRepository) {
        this.wishListRepository = wishListRepository;
        this.wishRepository = wishRepository;
    }

    // Tjekker titel og gemmer
    public int createWishList(String title, String description) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Titel må ikke være tom");
        }

        return wishListRepository.save(new WishList(title.trim(), description));
    }

    // UPDATE: updates a wish
    public Wish editWish(int id, Wish updated) {
        return wishListRepository.editWish(id, updated);
    }

    // UPDATE: updates a wishlist
    public WishList editWishList(int id, WishList updated) {
        return wishListRepository.editWishList(id, updated);
    }

    // DELETE a wish
    //The service receives the request from the controller and calls the repository.
    public boolean deleteWish(int wish_id) {

        return wishListRepository.deleteWish(wish_id);
    }

    // DELETE a wishlist
    public boolean deleteWishList(int wishList_id) {
        return wishListRepository.deleteWishList(wishList_id);
    }

    public List<Wish> getWishesByWishlistId(int wishlist_id) {
        return wishRepository.getWishesByWishlistId(wishlist_id);
    }

    public WishList getWishListById(int id) {
        return wishListRepository.getWishListById(id);
    }

    public void saveNewWish(int wishlist_id, Wish wish) {
        if (wish.getWish_name() == null || wish.getWish_name().isBlank()) {
            throw new IllegalArgumentException("Wish name must not be empty");
        }
        wish.setWishlist_id(wishlist_id);
        wishRepository.saveNewWish(wish);
    }

}
