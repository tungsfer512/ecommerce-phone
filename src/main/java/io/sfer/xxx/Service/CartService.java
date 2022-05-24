package io.sfer.xxx.Service;

import io.sfer.xxx.Model.Cart;
import io.sfer.xxx.Model.Product;
import io.sfer.xxx.Respository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
	
	@Autowired
	CartRepository cartRepository;
	@Autowired
	ProductService productService;
	
	public List<Cart> getAllCart() {
		return cartRepository.findAll();
	}
	
	public Long countExistedCartByUserIdAndProductId(Long user_id, Long product_id) {
		return cartRepository.countExistedCartByUserIdAndProductId(user_id, product_id);
	}
	
	public Cart getExistedCartByUserIdAndProductId(Long user_id, Long product_id) {
		return cartRepository.getExistedCartByUserIdAndProductId(user_id, product_id);
	}
	
	public Cart addCart(Cart cart) {
		Product product = productService.getProductById(cart.getProduct_id());
		if(product.getQuantity() > 0) {
			product.setQuantity(product.getQuantity() - 1);
			if(countExistedCartByUserIdAndProductId(cart.getUser_id(), cart.getProduct_id()) == 0) {
				return cartRepository.save(cart);
			}
			Cart te = getExistedCartByUserIdAndProductId(cart.getUser_id(), cart.getProduct_id());
			te.setQuantity(te.getQuantity() + 1);
	
			return updateCart(te);
		}
		return cart;
	}
	
	public void deleteCart(Long id) {
		cartRepository.deleteById(id);
	}
	
	public void deleteAllCartByUserId(Long id) {
		cartRepository.deleteAllCartByUserId(id);
	}
	
	public Cart updateCart(Cart cart) {
		return cartRepository.findById(cart.getId()).map(existedCart -> {
			existedCart.setQuantity(cart.getQuantity());
			return cartRepository.save(existedCart);
		}).orElseGet(() -> {
			return cartRepository.save(cart);
		});
	}
	
	public Long totalProductInCart(Long user_id) {
		return cartRepository.totalProductInCart(user_id);
	}
	
	public List<Cart> getCartByUserId(Long id) {
		return cartRepository.getAllCartByUserId(id);
	}
	
	public Cart getCartById(Long id) {
		return cartRepository.getCartById(id);
	}
}
