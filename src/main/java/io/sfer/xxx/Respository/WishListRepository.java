package io.sfer.xxx.Respository;

import io.sfer.xxx.Model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    @Query(value = "SELECT  * FROM wishlist WHERE user_id = :id", nativeQuery = true)
    public List<WishList> getAllWishListByUserId(@Param("id") Long id);

    @Query(value = "SELECT COUNT(*) FROM wishlist WHERE product_id=:product AND user_id=:user", nativeQuery = true)
    public Long countExistedWishListByUserIdAndProductId(@Param("user") Long user_id, @Param("product") Long product_id);

    @Query(value = "SELECT * FROM wishlist WHERE product_id=:product AND user_id=:user", nativeQuery = true)
    public WishList getExistedWishListByUserIdAndProductId(@Param("user") Long user_id, @Param("product") Long product_id);

    @Query(value = "SELECT SUM(quantity) FROM wishlist WHERE user_id=:id", nativeQuery = true)
    public Long totalProductInWishList(@Param("id") Long id);
}
