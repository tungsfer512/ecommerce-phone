package io.sfer.xxx.Respository;

import io.sfer.xxx.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
	
	@Query(value = "DELETE FROM cart WHERE user_id=:id;", nativeQuery = true)
	public void deleteAllCartByUserId(@Param("id") Long id);
	
	@Query(value = "SELECT COUNT(*) FROM cart WHERE product_id=:product AND user_id=:user", nativeQuery = true)
	public Long countExistedCartByUserIdAndProductId(@Param("user") Long user_id, @Param("product") Long product_id);
	
	@Query(value = "SELECT * FROM cart WHERE product_id=:product AND user_id=:user", nativeQuery = true)
	public Cart getExistedCartByUserIdAndProductId(@Param("user") Long user_id, @Param("product") Long product_id);
	
	@Query(value = "SELECT SUM(quantity) FROM cart WHERE user_id=:id", nativeQuery = true)
	public Long totalProductInCart(@Param("id") Long id);
	
	@Query(value = "SELECT * FROM cart WHERE user_id = :id", nativeQuery = true)
	public List<Cart> getAllCartByUserId(@Param("id") Long id);
	
	@Query(value = "SELECT * FROM cart WHERE id = :id", nativeQuery = true)
	public Cart getCartById(@Param("id") Long id);
	
}
