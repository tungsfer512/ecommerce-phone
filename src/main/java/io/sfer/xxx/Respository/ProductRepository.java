package io.sfer.xxx.Respository;

import io.sfer.xxx.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query(value = "SELECT DISTINCT(ram) from product ORDER BY ram ASC;", nativeQuery = true)
	public List<Long> getAllRam();
	
	@Query(value = "SELECT DISTINCT(rom) from product ORDER BY rom ASC;", nativeQuery = true)
	public List<Long> getAllRom();
	
	@Query(value = "SELECT * FROM product WHERE name LIKE %:keyword% OR brand LIKE %:keyword% OR description LIKE %:keyword%", nativeQuery = true)
	public List<Product> searchProduct(@Param("keyword") String keyword);
	
	@Query(value = "SELECT * FROM product WHERE category_id =:os AND ram = :ram AND rom = :rom AND (price BETWEEN :priceFrom AND :priceTo);", nativeQuery = true)
	public List<Product> filterProduct(@Param("os") Long os,
	                                   @Param("ram") Long ram,
	                                   @Param("rom") Long rom,
	                                   @Param("priceFrom") Long priceFrom,
	                                   @Param("priceTo") Long priceTo);
	@Query(value = "SELECT * FROM product WHERE status='hot' LIMIT 10;", nativeQuery = true)
	public List<Product> getTop10Hot();
}
