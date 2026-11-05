package ek.kea.wishlist_gruppe8.repository;

import ek.kea.wishlist_gruppe8.model.Wish;
import ek.kea.wishlist_gruppe8.model.WishRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Wish> rowMapper = new WishRowMapper();

    public WishRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveNewWish(Wish wish){
        String sql = "INSERT INTO wish(wishlist_id,wish_name,description,price,link_url) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql,wish.getWishlist_id(),wish.getWish_name(),wish.getWish_description(),wish.getPrice(),wish.getLink());
    }

    public List<Wish> getWishesByWishlistId (int wishlist_id) {
        String sql = """
                SELECT wish_id, wishlist_id, wish_name, description, link_url, price
                FROM wish
                WHERE wishlist_id = ? ORDER BY wish_id
                """;
        return jdbcTemplate.query(sql, rowMapper,wishlist_id);
    }

    public Wish getWishById(int wish_id) {
        String sql =
                """
            SELECT wish_id, wishlist_id, wish_name, description, link_url, price
            FROM wish
            WHERE wish_id =?
                        """;
        return jdbcTemplate.queryForObject(sql, rowMapper,wish_id);
    }
}
