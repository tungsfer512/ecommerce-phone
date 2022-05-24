package io.sfer.xxx.Service;

import io.sfer.xxx.Model.User;
import io.sfer.xxx.Respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleService roleService;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public List<User> getAllUser() {
		return userRepository.findAll();
	}
	
	public User addUser(User user) {
		return userRepository.save(user);
	}
	
	public User getUserByEmail(String email) {
		Optional<User> user = userRepository.findUserByEmail(email);
		return user.isPresent() ? user.get() : null;
	}
	
	public List<User> getAllCustomer() {
		List<User> users = getAllUser();
		List<User> res = new ArrayList<>();
		users.forEach(user -> {
			Long te = roleService.countRoleByUserId(user.getId());
			if(te == 1) {
				res.add(user);
			}
		});
		return res;
	}
	
	public User updateUser(User user) {
		return userRepository.findById(user.getId()).map(existedUser -> {
			existedUser.setFname(user.getFname());
			existedUser.setLname(user.getLname());
			existedUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			return userRepository.save(existedUser);
		}).orElseGet(() -> {
			return userRepository.save(user);
		});
	}
	
	public Long countUserByEmail(String email) {
		return userRepository.countUserByEmail(email);
	}
}
