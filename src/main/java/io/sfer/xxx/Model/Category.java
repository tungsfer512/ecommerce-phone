package io.sfer.xxx.Model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name = "name", nullable = false)
	String name;
	
}
