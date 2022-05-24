package io.sfer.xxx.Controller;

import io.sfer.xxx.Model.*;
import io.sfer.xxx.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
	
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;
	@Autowired
	CartService cartService;
	@Autowired
	WishListService wishListService;
	
	@GetMapping("/products")
	public String getAllProduct(Model model) {
		model.addAttribute("categoryList", categoryService.getAllCategory());
		model.addAttribute("productList", productService.getAllProduct());
		model.addAttribute("totalItems", productService.getAllProduct().size());
		model.addAttribute("ramList", productService.getAllRam());
		model.addAttribute("romList", productService.getAllRom());
		model.addAttribute("keyword", new String());
		model.addAttribute("productListHot", productService.getTop10Hot());
//		model.addAttribute("filterDTO", new OrderDTO().setPriceTo(100L));
		return "productList";
	}
	
	@PostMapping("/addToCart/{id}")
	public String addToCart(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetail customUserDetail) {
		System.out.println(customUserDetail.getEmail());
		System.out.println(customUserDetail.getId());
		System.out.println(customUserDetail.getFullName());
		Cart te = new Cart();
		te.setUser_id(userService.getUserByEmail(customUserDetail.getEmail()).getId());
		te.setProduct_id(id);
		te.setQuantity(1L);
		Product product = productService.getProductById(id);
		if (product.getQuantity() > 0) {
			cartService.addCart(te);
		}
		return "productList::cart_frag";
	}
	
	@GetMapping("/products/filter")
	public String getProductWithFilter( @Param("os") Long os,
	                                    @Param("ram") Long ram,
	                                    @Param("rom") Long rom,
	                                    @Param("priceFrom") Long priceFrom,
	                                    @Param("priceTo") Long priceTo,
	                                    Model model) {
		System.out.println(os);
		System.out.println(ram);
		System.out.println(rom);
		System.out.println(priceFrom);
		System.out.println(priceTo);
		model.addAttribute("categoryList", categoryService.getAllCategory());
		model.addAttribute("productList", productService.filterProduct(os, ram, rom, priceFrom, priceTo));
		model.addAttribute("totalItems", productService.filterProduct(os, ram, rom, priceFrom, priceTo).size());
		model.addAttribute("ramList", productService.getAllRam());
		model.addAttribute("romList", productService.getAllRom());
		model.addAttribute("os", os);
		model.addAttribute("ram", ram);
		model.addAttribute("rom", rom);
		model.addAttribute("priceFrom", priceFrom);
		model.addAttribute("priceTo", priceTo);
		model.addAttribute("keyword", new String());
		
		return "productList";
	}
	
	@GetMapping("/products/search")
	public  String search(Model model,
	                      @RequestParam("keyword") String keyword,
	                      @Param("os") Long os,
	                      @Param("ram") Long ram,
	                      @Param("rom") Long rom,
	                      @Param("priceFrom") Long priceFrom,
	                      @Param("priceTo") Long priceTo) {
		model.addAttribute("categoryList", categoryService.getAllCategory());
		model.addAttribute("productList", productService.searchProduct(keyword));
		model.addAttribute("totalItems", productService.searchProduct(keyword).size());
		model.addAttribute("ramList", productService.getAllRam());
		model.addAttribute("romList", productService.getAllRom());
		model.addAttribute("keyword", keyword);
		model.addAttribute("os", os);
		model.addAttribute("ram", ram);
		model.addAttribute("rom", rom);
		model.addAttribute("priceFrom", priceFrom);
		model.addAttribute("priceTo", priceTo);
		return "productList";
	}
	@GetMapping("/products/{id}")
	public String productDetail(Model model, @PathVariable Long id){
		model.addAttribute("product",productService.getProductById(id));
		return "productItem";
	}
	@GetMapping("/cart")
	public String redirectCart(Model model,@AuthenticationPrincipal CustomUserDetail customUserDetail){
		User user = userService.getUserByEmail(customUserDetail.getEmail());
		List<Cart> cartList = cartService.getCartByUserId(user.getId());
		Long totalSum= 0L;
		for(Cart x : cartList) {
			totalSum += (x.getQuantity() * x.getProduct().getPrice());
		}
		model.addAttribute("totalSum", totalSum);
		model.addAttribute("listCart",cartList);
		
		return "user/cart";
	}
	@GetMapping("/cart/delete/{id}")
	public String deleteCart(@PathVariable Long id) {
		cartService.deleteCart(id);
		return "redirect:/cart";
	}
	
	@GetMapping("/wishlist")
	public String disPlayWishLish(Model model,@AuthenticationPrincipal CustomUserDetail customUserDetail){
		User user = userService.getUserByEmail(customUserDetail.getEmail());
		model.addAttribute("wishList",wishListService.getAllWishlistByUserId(user.getId()));
		return "/user/wishList";
	}
	
	@GetMapping("/wishlist/delete/{id}")
	public String deteWishList(@PathVariable Long id){
		wishListService.deleteWishlist(id);
		return "redirect:/wishlist";
	}
	@PostMapping("/addToWishList/{id}")
	public String addToWishList(@PathVariable Long id,@AuthenticationPrincipal CustomUserDetail customUserDetail){
		WishList te = new WishList();
		te.setUser_id(userService.getUserByEmail(customUserDetail.getEmail()).getId());
		te.setProduct_id(id);
//		Product product = productService.getProductById(id);
		wishListService.addWishList(te);
		return "productList::wishList_frag";
	}
	
	@PostMapping("/updateCart/{id}/{quantity}")
	public String udpateCart(Model model,@AuthenticationPrincipal CustomUserDetail customUserDetail,@PathVariable Long id,@PathVariable Long quantity){
		Cart cart = cartService.getCartById(id);
		cart.setQuantity(quantity);
		cartService.updateCart(cart);
		User user = userService.getUserByEmail(customUserDetail.getEmail());
		model.addAttribute("listCart",cartService.getCartByUserId(user.getId()));
		return "user/cart";
	}
}
