package io.sfer.xxx.Respository;

import io.sfer.xxx.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findUserByEmail(String email);
	
	@Query(value = "SELECT COUNT (*) FROM user WHERE email = :email", nativeQuery = true)
	public Long countUserByEmail(@Param("email") String email);
}
