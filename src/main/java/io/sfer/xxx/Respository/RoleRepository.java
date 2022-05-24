package io.sfer.xxx.Respository;

import io.sfer.xxx.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	@Query(value = "SELECT COUNT(*) FROM userrole WHERE user_id = :id", nativeQuery = true)
	public Long countRoleByUserId(@Param("id") Long id);
}
