package ek.kea.wishlist_gruppe8.model;

public class WishList {
    private int id;
    private String title;
    private String description;
    private String shareToken;

    public WishList(String title, String description, String shareToken) {
        this.title = title;
        this.description = description;
        this.shareToken = shareToken;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getShareToken() {
        return shareToken;
    }
}
