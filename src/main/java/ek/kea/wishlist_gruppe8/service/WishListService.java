package ek.kea.wishlist_gruppe8.service;
import ek.kea.wishlist_gruppe8.model.WishList;
import ek.kea.wishlist_gruppe8.repository.WishListRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
        String token = UUID.randomUUID().toString();
        wishListRepository.save(new WishList(title, description, token));
    }
}
