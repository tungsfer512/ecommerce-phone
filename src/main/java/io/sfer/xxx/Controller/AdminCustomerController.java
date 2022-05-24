package io.sfer.xxx.Controller;

import io.sfer.xxx.Model.User;
import io.sfer.xxx.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminCustomerController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/admin/customers")
	public String getAllCUstomer(Model model) {
		List<User> customers = userService.getAllCustomer();
		model.addAttribute("customers", customers);
		return "admin/customers";
	}
}
