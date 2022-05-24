package io.sfer.xxx.Controller;


import io.sfer.xxx.Model.*;
import io.sfer.xxx.Respository.OrderProductRepository;
import io.sfer.xxx.Service.CartService;
import io.sfer.xxx.Service.OrderProductService;
import io.sfer.xxx.Service.OrderService;
import io.sfer.xxx.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	OrderService orderService;
	@Autowired
	CartService cartService;
	@Autowired
	OrderProductService orderProductService;
	
	@GetMapping("/profile")
	public String displayProfile(Model model, @AuthenticationPrincipal CustomUserDetail customUserDetail){
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		CustomUserDetail customUserDetail = (CustomUserDetail) auth.getPrincipal();
//		Object user = SecurityContextHolder.getContext().getAuthentication()
//			.getPrincipal();
//		String emsil = user.getName();
//		String emsil= ;
//		CustomUserDetail customUserDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		System.out.println(user.getPrincipal().getAttributes().get("email"));
		model.addAttribute("user",userService.getUserByEmail(customUserDetail.getEmail()));
		return "user/profile";
	}
	@GetMapping("/refreshPass")
	public String redirectRenewPass(){
//		model.addAttribute("password",new String());
		return "user/renewPassword";
	}
	@PostMapping("/refreshPass")
	public String renewPass(@Param("password") String password, @AuthenticationPrincipal CustomUserDetail customUserDetail){
		System.out.println(customUserDetail.getPassword());
		System.out.println(password);
		User user=userService.getUserByEmail(customUserDetail.getEmail());
		user.setPassword(bCryptPasswordEncoder.encode(password));
//		customUserDetail.setPassword(password);
		userService.updateUser(user);
		System.out.println(customUserDetail.getPassword());
		return "redirect:/";
	}
	@GetMapping("/checkout")
	public String checkout(Model model, @AuthenticationPrincipal CustomUserDetail customUserDetail) {
		model.addAttribute("orderInfo", new OrderInfo());
		User user = userService.getUserByEmail(customUserDetail.getEmail());
		List<Cart> cartList =  cartService.getCartByUserId(user.getId());
		model.addAttribute("cartList",cartList);
		Long totalSub = 0L;
		for(Cart x : cartList) {
			totalSub += (x.getQuantity() * x.getProduct().getPrice());
		}
		model.addAttribute("totalSub", totalSub);
		return "user/checkout";
	}
	
	@PostMapping("/checkout")
	public String checkoutPost(@ModelAttribute OrderInfo orderInfo, Model model, @AuthenticationPrincipal CustomUserDetail customUserDetail) {
		User user = userService.getUserByEmail(customUserDetail.getEmail());
		orderInfo.setUser_id(user.getId());
		System.out.println(orderInfo);
		OrderInfo xx = orderService.addOrder(orderInfo);
		List<Cart> cartList = cartService.getCartByUserId(user.getId());
		
//		System.out.println(cartList);
		for(Cart x : cartList) {
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setOrder_id(xx.getId());
			orderProduct.setProduct_id(x.getProduct_id());
			orderProduct.setQuantity(x.getQuantity());
			orderProductService.addOrderProduct(orderProduct);
			cartService.deleteCart(x.getId());
		}
//		cartService.deleteAllCartByUserId(user.getId());
		return "redirect:/products";
	}
	@GetMapping("/onlineCheckout")
	public String onlineCheckout() {
		return "user/onlineCheckout";
	}
}
