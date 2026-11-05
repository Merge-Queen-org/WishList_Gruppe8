package ek.kea.wishlist_gruppe8.model;

import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WishRowMapper implements RowMapper<Wish> {
    @Override
    public Wish mapRow(ResultSet rs, int RowNum) throws SQLException {
        int wishId = rs.getInt("wish_id");
        int wishlistId = rs.getInt("wishlist_id");
        String name = rs.getString("wish_name");
        String description= rs.getString("description");
        String link = rs.getString("link_url");
        BigDecimal price = rs.getBigDecimal("price");

        return new Wish(wishId,wishlistId,name, description,link,price);
    }
}

