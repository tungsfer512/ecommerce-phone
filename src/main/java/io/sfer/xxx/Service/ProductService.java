package io.sfer.xxx.Service;

import io.sfer.xxx.Model.Product;
import io.sfer.xxx.Respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	
	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}
	public Product getProductById(Long id) {
		return productRepository.findById(id).get();
	}
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}
	public void deleteProductById(Long id) {
		productRepository.deleteById(id);
	}
	public Product updateProduct(Product product) {
		return productRepository.findById(product.getId()).map(existedProduct -> {
			existedProduct.setName(product.getName());
			existedProduct.setColor(product.getColor());
			existedProduct.setBrand(product.getBrand());
			existedProduct.setPrice(product.getPrice());
			existedProduct.setQuantity(product.getQuantity());
			existedProduct.setQuality(product.getQuality());
			existedProduct.setDescription(product.getDescription());
			existedProduct.setImage(product.getImage());
			existedProduct.setStatus(product.getStatus());
			existedProduct.setCategory_id(product.getCategory_id());
			return productRepository.save(existedProduct);
		}).orElseGet(() -> {
			return productRepository.save(product);
		});
	}
	
	public List<Long> getAllRam() {
		return productRepository.getAllRam();
	}
	
	public List<Long> getAllRom() {
		return productRepository.getAllRom();
	}
	
	public List<Product> searchProduct(String key) {
		return productRepository.searchProduct(key);
	}
	
	public List<Product> filterProduct(Long os, Long ram, Long rom, Long priceFrom, Long priceTo) {
		List<Product> res = productRepository.filterProduct(os, ram, rom, priceFrom, priceTo);
		System.out.println(res);
		return res;
	}
	
	public List<Product> getTop10Hot(){return productRepository.getTop10Hot();}
	
}
