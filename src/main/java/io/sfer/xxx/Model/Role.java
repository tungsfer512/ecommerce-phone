package io.sfer.xxx.Model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name = "name", nullable = false)
	@NotEmpty
	String name;
	
	@ManyToMany(mappedBy = "roles")
	List<User> users;
	
	public Role(String name) {
		this.name = name;
	}
}
