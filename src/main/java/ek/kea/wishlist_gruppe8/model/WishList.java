package ek.kea.wishlist_gruppe8.model;

import java.util.List;

public class WishList {
    private int wishList_id;
    private String wishList_name;
    private String wishList_description;
    private String shareToken;
    private List<Wish> wishes;


    public WishList(int wishList_id, String wishList_name, String wishList_description, String shareToken, List<Wish> wishes) {
        this.wishList_id = wishList_id;
        this.wishList_name = wishList_name;
        this.wishList_description = wishList_description;
        this.shareToken = shareToken;
        this.wishes = wishes;
    }

    // Getters and Setters
    public int getWishList_id() {
        return wishList_id;
    }

    public void setWishList_id(int wishList_id) {
        this.wishList_id = wishList_id;
    }

    public String getWishList_name() {
        return wishList_name;
    }

    public void setWishList_name(String wishList_name) {
        this.wishList_name = wishList_name;
    }

    public String getWishList_description() {
        return wishList_description;
    }
    public void setWishList_description(String wishList_description) {
        this.wishList_description = wishList_description;
    }

    public String getShareToken() {
        return shareToken;
    }

    public void setShareToken(String shareToken) {
        this.shareToken = shareToken;
    }

    public List<Wish> getWishes() {
        return wishes;
    }

    public void setWishes(List<Wish> wishes) {
        this.wishes = wishes;
    }
}

