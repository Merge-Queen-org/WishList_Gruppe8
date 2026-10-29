package ek.kea.wishlist_gruppe8.model;

public class Wish {
    private int wish_id;
    private String wish_name;
    private String wish_description;
    private String link;
    private double price;


    public Wish(int wish_id, String name, String description, String link, double price) {
        this.wish_id = wish_id;
        this.wish_name = name;
        this.wish_description = description;
        this.link = link;
        this.price = price;
    }

    public int getWish_id() {
        return wish_id;
    }

    public void setWish_id(int wish_id) {
        this.wish_id = wish_id;
    }

    public String getWish_name() {
        return wish_name;
    }

    public void setWish_name(String name) {
        this.wish_name = wish_name;
    }

    public String getWishDescription() {
        return wish_description;
    }

    public void setWishDescription(String description) {
        this.wish_description = wish_description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}