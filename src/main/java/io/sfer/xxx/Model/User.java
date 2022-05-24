package io.sfer.xxx.Model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name = "email", nullable = false)
	@NotEmpty
	@Email(message = "{error.invalid_email}")
	String email;
	
	@Column(name = "password", nullable = false)
	String password;
	
	@Column(name = "fname")
	@NotEmpty
	String fname;
	
	@Column(name = "lname")
	@NotEmpty
	String lname;
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(
		name = "userrole",
		joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_userrole_user"))},
		inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_userrole_role"))}
	)
	List<Role> roles;
	
	public User(User user) {
		this.fname = user.getFname();
		this.lname = user.getLname();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.roles = user.getRoles();
	}
	
	public String getFullName() {
		return (this.fname + " " + this.lname);
	}
}
