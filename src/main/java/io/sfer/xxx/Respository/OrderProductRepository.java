package io.sfer.xxx.Respository;

import io.sfer.xxx.Model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
	@Query(value = "SELECT * FROM orderproduct WHERE order_id = :id", nativeQuery = true)
	public List<OrderProduct> getAllOrderProductByUserId(@Param("id") Long id);
}
