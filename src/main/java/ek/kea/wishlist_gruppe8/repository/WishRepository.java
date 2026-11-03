package ek.kea.wishlist_gruppe8.repository;

import ek.kea.wishlist_gruppe8.model.Wish;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishRepository {
    private final JdbcTemplate jdbcTemplate;

    public WishRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveNewWish(Wish wish){
        String sql = "INSERT INTO wish(wishlist_id,wish_name,description,price,link_url) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql,wish.getWishlist_id(),wish.getWish_name(),wish.getWish_description(),wish.getPrice(),wish.getLink());
    }

    public List<Wish> getWishesByWishlistId (int wishlist_id){
        String sql = """
                SELECT wish_id, wishlist_id, wish_name, description, link_url, price
                FROM wish
                WHERE wishlist_id = ? ORDER BY wish_id
                """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Wish(
                rs.getInt("wish_id"),
                rs.getInt("wishlist_id"),
                rs.getString("wish_name"),
                rs.getString("description"),
                rs.getString("link_url"),
                rs.getBigDecimal("price")), wishlist_id);
    }

    public Wish getWishById(int wish_id) {
        String sql = """
            SELECT wish_id, wishlist_id, wish_name, description, link_url, price
            FROM wish
            WHERE wish_id = ?
            """;
        List<Wish> result = jdbcTemplate.query(sql, (rs, rowNum) -> new Wish(
                rs.getInt("wish_id"),
                rs.getInt("wishlist_id"),
                rs.getString("wish_name"),
                rs.getString("description"),
                rs.getString("link_url"),
                rs.getBigDecimal("price")), wish_id);
        return result.isEmpty() ? null : result.get(0);
    }
}
