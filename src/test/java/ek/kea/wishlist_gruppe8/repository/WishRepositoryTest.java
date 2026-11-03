package ek.kea.wishlist_gruppe8.repository;

import ek.kea.wishlist_gruppe8.model.Wish;
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

public class WishRepositoryTest {
    @Autowired
    private WishRepository wishRepo;

    @Test
    void getWishesByWishlistId(){
        List<Wish> all = wishRepo.getWishesByWishlistId(1);
        assertThat(all.size()).isEqualTo(3);
        assertThat(all.get(0).getWish_name()).isEqualTo("Bday");
        assertThat(all.get(1).getWish_name()).isEqualTo("Wedding");
        assertThat(all.get(2).getWish_name()).isEqualTo("Christmas");
    }

    @Test
    void getWishesByWishlistId_notFound(){
        assertThat(wishRepo.getWishesByWishlistId(999)).isEmpty();
    }
}
