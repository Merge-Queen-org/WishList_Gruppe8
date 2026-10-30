
package ek.kea.wishlist_gruppe8.repository;

import ek.kea.wishlist_gruppe8.model.Wish;
import ek.kea.wishlist_gruppe8.model.WishList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WishListRepository {

    private final JdbcTemplate jdbcTemplate;

    public WishListRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // UPDATE: updates a wish by ID
    public Wish updateWish(int id, Wish updated) {
        String sql = """
                UPDATE wish
                SET wish_name = ?,
                    description = ?,
                    link_url = ?,
                    price = ?
                WHERE wish_id = ?
                """;

        int rows = jdbcTemplate.update(
                sql,
                updated.getWish_name(),
                updated.getWishDescription(),
                updated.getLink(),
                updated.getPrice(),
                id
        );

        if (rows > 0) {
            return updated;
        }

        return null;
    }

    // UPDATE: updates a wishlist by ID
    public WishList updateWishList(int id, WishList updated) {
        String sql = """
                UPDATE wishlist
                SET wishlist_name = ?,
                    description = ?
                WHERE wishlist_id = ?
                """;

        int rows = jdbcTemplate.update(
                sql,
                updated.getWishList_name(),
                updated.getWishList_description(),
                updated.getShareToken(),
                id
        );

        if (rows > 0) {
            return updated;
        }

        return null;
    }
}
