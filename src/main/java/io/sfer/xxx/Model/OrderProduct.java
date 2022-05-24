package io.sfer.xxx.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "orderproduct")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class OrderProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name = "product_id")
	Long product_id;
	
	@Column(name = "quantity")
	Long quantity;
	@Column(name = "order_id")
	Long order_id;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(
		name = "order_id",
		referencedColumnName = "id",
		foreignKey = @ForeignKey(name = "FK_product_orderproduct"),
		insertable = false,
		updatable = false,
		nullable = false
	)
	@JsonIgnoreProperties(value = { "applications", "hiber nateLazyInitializer" })
	OrderInfo orderInfo;
}
