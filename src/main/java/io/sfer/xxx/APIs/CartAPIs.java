package io.sfer.xxx.APIs;

import io.sfer.xxx.Respository.UserRepository;
import io.sfer.xxx.Service.CartService;
import io.sfer.xxx.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartAPIs {

	@Autowired
	CartService cartService;

	@Autowired
	UserRepository userService;

	@GetMapping("/apis/countcart/{id}")
	public Long countCart(@PathVariable Long id) {
		return cartService.totalProductInCart(id);
	}

	@GetMapping("/apis/getUserIdByEmail/{email}")
	public Long getUserIdByEmail(@PathVariable String email) {
		return userService.findUserByEmail(email).get().getId();
	}
}
