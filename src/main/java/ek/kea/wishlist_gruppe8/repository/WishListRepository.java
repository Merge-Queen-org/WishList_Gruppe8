package ek.kea.wishlist_gruppe8.repository;

import ek.kea.wishlist_gruppe8.model.Wish;
import ek.kea.wishlist_gruppe8.model.WishList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishListRepository {

    private final JdbcTemplate jdbcTemplate;

    public WishListRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
        list.setWishes(getWishesByWishlistId(id));
        return list;
    }

    public List<Wish> getWishesByWishlistId (int wishlist_id){
        String sql = """
                SELECT wish_id, wishlist_id, wish_name, description, link_url, price
                FROM wish
                WHERE wishlist_id = ?
                """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Wish(
                rs.getInt("wish_id"),
                rs.getInt("wishlist_id"),
                rs.getString("wish_name"),
                rs.getString("description"),
                rs.getString("link_url"),
                rs.getBigDecimal("price")), wishlist_id);
    }
}
