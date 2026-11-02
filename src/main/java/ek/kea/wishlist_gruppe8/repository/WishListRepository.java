package ek.kea.wishlist_gruppe8.repository;

import ek.kea.wishlist_gruppe8.model.WishList;
import org.springframework.stereotype.Repository;

@Repository
public class WishListRepository<JdbcTemplate> {

    private final JdbcTemplate jdbcTemplate;

    public WishListRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Gemmer en ny ønskeseddel i databasen
    public void save(WishList wishList) {
        String sql = "INSERT INTO wishlist (title, description) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, wishList.getTitle(), wishList.getDescription());
    }
}