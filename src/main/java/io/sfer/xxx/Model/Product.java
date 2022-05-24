package io.sfer.xxx.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name = "name", nullable = false)
	String name;
	
	@Column(name = "color", nullable = false)
	String color;
	
	@Column(name = "brand", nullable = false)
	String brand;
	
	@Column(name = "price", nullable = false)
	Long price;
	
	@Column(name = "quantity", nullable = false)
	Long quantity;
	
	@Column(name = "quality", nullable = false)
	String quality;
	
	@Column(name = "description", nullable = false)
	String description;
	
	@Column(name = "image", nullable = false)
	String image;
	
	@Column(name = "status", nullable = false)
	String status;
	
	@Column(name = "sold", nullable = false)
	Long sold;
	
	@Column(name = "ram", nullable = false)
	Long ram;
	
	@Column(name = "rom", nullable = false)
	Long rom;
	
	@Column(name = "category_id", nullable = false)
	Long category_id;
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(
		name = "category_id",
		referencedColumnName = "id",
		foreignKey = @ForeignKey(name = "FK_product_category"),
		insertable = false,
		updatable = false,
		nullable = false
	)
	@JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
	Category category;
}
