package io.sfer.xxx.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.sfer.xxx.Model.Role;
import io.sfer.xxx.Model.User;
import io.sfer.xxx.Service.ProductService;
import io.sfer.xxx.Service.RoleService;
import io.sfer.xxx.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	ProductService productService;
	
	@GetMapping({"/", "/index", "/home"})
	public String index(Model model) {
		model.addAttribute("productList",productService.getTop10Hot());
//		model.addAttribute("user")
		return "index";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	@GetMapping("/register")
	public String registerGet(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String registerPost(@ModelAttribute User user) throws IOException, InterruptedException {
		String link= "redirect:/login";
		List<Role> roles = new ArrayList<>();
//		if(userService.getUserByEmail(user.getEmail()) != null) {
//			return "redirect:/register";
//		} else {
			roles.add(roleService.getRolebyId(2L));
			user.setRoles(roles);
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			userService.addUser(user);
//		}
		return "redirect:/login";
	}
	

}
