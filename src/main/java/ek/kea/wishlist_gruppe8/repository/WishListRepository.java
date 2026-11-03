package ek.kea.wishlist_gruppe8.repository;

import ek.kea.wishlist_gruppe8.model.Wish;
import ek.kea.wishlist_gruppe8.model.WishList;
import ek.kea.wishlist_gruppe8.model.WishListRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class WishListRepository {

    private final JdbcTemplate jdbcTemplate;
    private final WishRepository wishRepository;
/*You create an instance of WishListRowMapper inside your
WishListRepository because the repository needs a RowMapper object to convert database rows
 into Java objects when you execute a SELECT query.*/
    private final RowMapper<WishList> rowMapper = new WishListRowMapper();

    public WishListRepository(JdbcTemplate jdbcTemplate, WishRepository wishRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.wishRepository = wishRepository;
    }

    // Gemmer en ny ønskeseddel og returnerer det nye id
    public int save(WishList wishList) {
        String sql = "INSERT INTO wishlist (wishlist_name, description) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder(); // gemmer id'et som databasen laver
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, wishList.getWishList_name());
            ps.setString(2, wishList.getWishList_description());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
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
        /*Here you are creating another RowMapper using a lambda expression.
But you already created a separate class called WishListRowMapper.
You can replace that code with:
List<WishList> result = jdbcTemplate.query(sql, rowMapper, id);
Much cleaner!*/
        List<WishList> result = jdbcTemplate.query(sql, rowMapper, id);
      /*  List<WishList> result = jdbcTemplate.query(sql,(rs,rowNum) -> new WishList (
                rs.getInt("wishlist_id"),
                rs.getString("wishlist_name"),
                rs.getString("description"), null),id);*/
        if (result.isEmpty()) return  null;  //fetch the wishlist, then fetch its wishes.
        WishList list = result.get(0);       // It avoids running a query inside a row mapper.
        list.setWishes(wishRepository.getWishesByWishlistId(id)); //separately retrieve all wishes belonging to that wishlist.
        return list;
    }
}
