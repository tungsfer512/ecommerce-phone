package io.sfer.xxx.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	
	@GetMapping({"/admin/dashboard", "/admin"})
	public String dashboard(Model model) {
		return "/admin/dashboard";
	}
	
	@GetMapping("/admin/map")
	public String map() {
		return "admin/map";
	}
}
