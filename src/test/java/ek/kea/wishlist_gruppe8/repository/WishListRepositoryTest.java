package ek.kea.wishlist_gruppe8.repository;

import ek.kea.wishlist_gruppe8.model.Wish;
import ek.kea.wishlist_gruppe8.model.WishList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:h2init.sql", executionPhase = BEFORE_TEST_METHOD)
public class WishListRepositoryTest {
    @Autowired
    private WishListRepository repo;

    @Test
    void getWishesByWishlistId(){
        List<Wish> all = repo.getWishesByWishlistId(1);
        assertThat(all).isNotNull();
        assertThat(all.size()).isEqualTo(3);
        assertThat(all.get(0).getWish_name()).isEqualTo("Bday");
        assertThat(all.get(1).getWish_name()).isEqualTo("Wedding");
        assertThat(all.get(2).getWish_name()).isEqualTo("Christmas");
    }

    @Test
    void getWishlistByID(){
        WishList list = repo.getWishListById(1);
        assertThat(list).isNotNull();
        assertThat(list.getWishList_name()).isEqualTo("Birthday");
        assertThat(list.getWishes()).hasSize(3);
    }

    @Test
    void save(){
        int id = repo.save(new WishList("Jul", "Julegaver")); // gem ny ønskeseddel
        WishList list = repo.getWishListById(id);           // hent den igen
        assertThat(list.getWishList_name()).isEqualTo("Jul");
        assertThat(list.getWishList_description()).isEqualTo("Julegaver");
    }

    @Test
    void getWishListById_notFound(){
        assertThat(repo.getWishListById(999)).isNull();
        assertThat(repo.getWishesByWishlistId(999)).isEmpty();
    }
}