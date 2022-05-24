package io.sfer.xxx.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name = "user_id", nullable = false)
	Long user_id;
//	@ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
//	@JoinColumn(
//		name = "user_id",
//		referencedColumnName = "id",
//		foreignKey = @ForeignKey(name = "FK_cart_user"),
//		insertable = false,
//		updatable = false,
//		nullable = false
//	)
//	@JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
//	User user;
	
	@Column(name = "product_id", nullable = false)
	Long product_id;
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(
		name = "product_id",
		referencedColumnName = "id",
		foreignKey = @ForeignKey(name = "FK_cart_product"),
		insertable = false,
		updatable = false,
		nullable = false
	)
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer" })
	Product product;
	
	@Column(name = "quantity", nullable = false)
	Long quantity;
}
