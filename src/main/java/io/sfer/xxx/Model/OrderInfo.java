package io.sfer.xxx.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "orderinfo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class OrderInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name = "address", nullable = false)
	String address;
	
	@Column(name = "city", nullable = false)
	String city;
	
	@Column(name = "zip", nullable = false)
	String zip;
	
	@Column(name = "country", nullable = false)
	String country;
	
	@Column(name = "phone", nullable = false)
	String phone;
	
	@Column(name = "email", nullable = false)
	String email;
	
	@Column(name = "f_name", nullable = false)
	String first_name;
	
	@Column(name = "l_name", nullable = false)
	String last_name;
	
	@Column(name = "user_id", nullable = false)
	Long user_id;
//
//	@ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
//	@JoinColumn(
//		name = "user_id",
//		referencedColumnName = "id",
//		foreignKey = @ForeignKey(name = "FK_orderinfo_user"),
//		insertable = false,
//		updatable = false,
//		nullable = false
//	)
//	@JsonIgnoreProperties(value = { "applications", "hiber nateLazyInitializer" })
//	User user;
}
