package ek.kea.wishlist_gruppe8.repository;

import ek.kea.wishlist_gruppe8.model.WishList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:h2init.sql", executionPhase = BEFORE_TEST_METHOD)
public class WishListRepositoryTest {
    @Autowired
    private WishListRepository repo;

    @Test
    void getWishlistByID(){
        WishList list = repo.getWishListById(1);
        assertThat(list).isNotNull();
        assertThat(list.getWishList_name()).isEqualTo("Birthday");
        assertThat(list.getWishes()).hasSize(3);
    }

    @Test
    void getWishListById_notFound(){
        assertThat(repo.getWishListById(999)).isNull();
    }
}