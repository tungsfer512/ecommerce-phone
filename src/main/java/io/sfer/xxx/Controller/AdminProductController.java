package io.sfer.xxx.Controller;

import io.sfer.xxx.Model.Product;
import io.sfer.xxx.Service.CategoryService;
import io.sfer.xxx.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class AdminProductController {
	
	public static String uploadDir =
		System.getProperty("user.dir") + "/src/main/resources/static/productImages";
	
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/admin/products")
	public String adminGetAllProducts(Model model) throws IOException, InterruptedException {
		model.addAttribute("productList", productService.getAllProduct());
		return "admin/products";
	}
	
	@GetMapping("/admin/products/add")
	public String adminAddProductGet(Model model) throws IOException, InterruptedException {
		model.addAttribute("product", new Product());
		model.addAttribute("categoryList", categoryService.getAllCategory());
		return "admin/addProduct";
	}
	
	@PostMapping("/admin/products/add")
	public String adminAddProductPost(@ModelAttribute Product product,
	                                  @RequestParam("productImage") MultipartFile file,
	                                  @RequestParam("imgName") String imgName) throws IOException, InterruptedException {
		String imgUUID;
		if (!file.isEmpty()) {
			imgUUID = file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imgUUID);
			Files.write(fileNameAndPath, file.getBytes());
		} else {
			imgUUID = imgName;
		}
		product.setImage(imgUUID);
		product.setSold(0L);
		productService.addProduct(product);
		return "redirect:/admin/products/add";
	}
	
	@GetMapping("admin/products/edit/{id}")
	public String adminUpdateProductGet(Model model, @PathVariable Long id) throws IOException, InterruptedException {
		model.addAttribute("product", productService.getProductById(id));
		model.addAttribute("categoryList", categoryService.getAllCategory());
		return "admin/addProduct";
	}
	
	@PostMapping("/admin/products/edit/{id}")
	public String adminUpdateProductPost(@ModelAttribute Product product,
	                                     @RequestParam("productImage") MultipartFile file,
	                                     @RequestParam("imgName") String imgName,
	                                     @PathVariable Long id) throws IOException, InterruptedException {
		String imgUUID;
		if (!file.isEmpty()) {
			imgUUID = file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imgUUID);
			Files.write(fileNameAndPath, file.getBytes());
		} else {
			imgUUID = imgName;
		}
		product.setImage(imgUUID);
		product.setStatus("on");
		productService.updateProduct(product);
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/products/delete/{id}")
	public String adminDeleteProduct(@PathVariable Long id) throws IOException, InterruptedException {
		productService.deleteProductById(id);
		return "redirect:/admin/products";
	}
	
	
}
