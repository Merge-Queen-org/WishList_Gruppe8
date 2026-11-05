
package ek.kea.wishlist_gruppe8.service;

import ek.kea.wishlist_gruppe8.model.Wish;
import ek.kea.wishlist_gruppe8.model.WishList;
import ek.kea.wishlist_gruppe8.repository.WishListRepository;
import ek.kea.wishlist_gruppe8.repository.WishRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // UPDATE: updates a wish. Returns null if the wish doesn't exist.
    public Wish editWish(int id, Wish updated) {
        validateWish(updated);
        Wish existing = wishRepository.getWishById(id);
        if (existing == null) return null;
        updated.setWish_id(id);
        updated.setWishlist_id(existing.getWishlist_id()); // never trust the wishlist id from the form
        return wishListRepository.editWish(id, updated);
    }

    // UPDATE: updates a wishlist. Returns null if the wishlist doesn't exist.
    public WishList editWishList(int id, WishList updated) {
        if (updated.getWishList_name() == null || updated.getWishList_name().isBlank()) {
            throw new IllegalArgumentException("Name must not be empty");
        }
        updated.setWishList_name(updated.getWishList_name().trim());
        return wishListRepository.editWishList(id, updated);
    }

    // DELETE a wish
    //The service receives the request from the controller and calls the repository.
    public boolean deleteWish(int wish_id) {

        return wishListRepository.deleteWish(wish_id);
    }

    // DELETE a wishlist @Transactional is important because you are performing
    // two database operations that belong together when deleting a wishlist.
    @Transactional
    public boolean deleteWishList(int wishList_id) {
        return wishListRepository.deleteWishList(wishList_id);
    }
/*it as an agreement:
- If everything succeeds → COMMIT (save the changes).
- If something fails → ROLLBACK (undo the changes).*/
    public List<Wish> getWishesByWishlistId(int wishlist_id) {
        return wishRepository.getWishesByWishlistId(wishlist_id);
    }

    public WishList getWishListById(int id) {
        return wishListRepository.getWishListById(id);
    }

    public Wish getWishById(int wish_id) {
        return wishRepository.getWishById(wish_id);
    }

    public void saveNewWish(int wishlist_id, Wish wish) {
        validateWish(wish);
        wish.setWishlist_id(wishlist_id);
        wishRepository.saveNewWish(wish);
    }

    // Shared by create and edit: name required, price not negative, link must be http(s)
    private void validateWish(Wish wish) {
        if (wish.getWish_name() == null || wish.getWish_name().isBlank()) {
            throw new IllegalArgumentException("Wish name must not be empty");
        }
        wish.setWish_name(wish.getWish_name().trim());
        if (wish.getPrice() != null && wish.getPrice().signum() < 0) {
            throw new IllegalArgumentException("Price must not be negative");
        }
        String link = wish.getLink();
        if (link == null || link.isBlank()) {
            wish.setLink(null);
        } else {
            link = link.trim();
            String lower = link.toLowerCase();
            if (!lower.startsWith("http://") && !lower.startsWith("https://")) { // blocks javascript: links
                throw new IllegalArgumentException("Link must start with http:// or https://");
            }
            wish.setLink(link);
        }
    }

    public List<WishList> getAllWishLists() {
        return wishListRepository.getAllWishLists();
    }



}
