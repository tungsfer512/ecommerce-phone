package io.sfer.xxx.Service;

import io.sfer.xxx.Model.Role;
import io.sfer.xxx.Respository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
	
	@Autowired
	RoleRepository roleRepository;
	
	public Role getRolebyId(Long id) {
		return roleRepository.findById(id).get();
	}
	
	public Long countRoleByUserId(Long id) {
		return roleRepository.countRoleByUserId(id);
	}
}
