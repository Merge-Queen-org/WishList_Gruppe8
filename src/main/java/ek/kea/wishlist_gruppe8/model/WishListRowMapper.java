package ek.kea.wishlist_gruppe8.model;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
// implement the rowmapper with the class name we need to use
public class WishListRowMapper implements RowMapper<WishList> {
    @Override
   // The interface requires the method to be public.
    public WishList mapRow(ResultSet rs, int RowNum) throws SQLException {
        //tell Java which database column to retrieve:
        int wishList_id = rs.getInt("wishList_id");
        String wishList_name = rs.getString("wishList_name");
        String wishList_description = rs.getString("wishList_description");

        return new WishList(wishList_id,wishList_name, wishList_description,null);
   }
           }
/* what i do next: How to use it
 Inside your WishListRepository, you need to create an instance*/