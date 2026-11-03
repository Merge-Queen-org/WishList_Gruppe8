package ek.kea.wishlist_gruppe8.repository;

import ek.kea.wishlist_gruppe8.model.Wish;
import ek.kea.wishlist_gruppe8.model.WishList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishListRepository {

    private final JdbcTemplate jdbcTemplate;
    private final WishRepository wishRepository;

    public WishListRepository(JdbcTemplate jdbcTemplate, WishRepository wishRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.wishRepository = wishRepository;
    }

    // Gemmer en ny ønskeseddel i databasen
    public void save(WishList wishList) {
        String sql = "INSERT INTO wishlist (wishlist_name, description) VALUES (?, ?)";
        jdbcTemplate.update(sql, wishList.getWishList_name(), wishList.getWishList_description());
    }

    // UPDATE: updates a wish by ID
    public Wish editWish(int id, Wish updated) {
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
                updated.getWish_description(),
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
    public WishList editWishList(int id, WishList updated) {
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
                id
        );

        if (rows > 0) {
            return updated;
        }

        return null;

    }
    // DELETE: deletes a wish
    public boolean deleteWish(int wish_id) {
        return jdbcTemplate.update(
                "DELETE FROM wish WHERE wish_id = ?",
                wish_id
        ) > 0;
    }
    /*The > 0 checks whether at least one row was affected.
Therefore:
- If the result is greater than 0 → true.
- If the result is 0 → false.*/

    // DELETE: deletes a wishlist
    public boolean deleteWishList(int wishList_id) {
        return jdbcTemplate.update(
                "DELETE FROM wishlist WHERE wishlist_id = ?",
                wishList_id
        ) > 0;
    }

    public WishList getWishListById(int id){
        String sql = """
                SELECT wishlist_id, wishlist_name, description
                FROM wishlist
                WHERE wishlist_id = ?
                """;
        List<WishList> result = jdbcTemplate.query(sql,(rs,rowNum) -> new WishList (
                rs.getInt("wishlist_id"),
                rs.getString("wishlist_name"),
                rs.getString("description"), null),id);
        if (result.isEmpty()) return  null;  //fetch the wishlist, then fetch its wishes.
        WishList list = result.get(0);       // It avoids running a query inside a row mapper.
        list.setWishes(wishRepository.getWishesByWishlistId(id));
        return list;
    }
}
