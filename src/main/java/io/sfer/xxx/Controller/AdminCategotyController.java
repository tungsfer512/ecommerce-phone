package io.sfer.xxx.Controller;

import io.sfer.xxx.Model.Category;
import io.sfer.xxx.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;

@Controller
public class AdminCategotyController {
	
	@Autowired
	CategoryService categoryService;
	
	public static String uploadDir =
		System.getProperty("user.dir") + "/src/main/resources/static/productImages";
	
	@GetMapping("/admin/categories")
	public String adminGetCategories(Model model) throws IOException, InterruptedException {
		List<Category> categoryList = categoryService.getAllCategory();
		System.out.println(categoryList);
//		model.addAttribute("categoryList", new ArrayList<>());
		model.addAttribute("categoryList", categoryList);
		return "admin/categories";
	}
	
	@GetMapping("/admin/categories/add")
		public String adminAddCategoryGet(Model model) {
		model.addAttribute("category", new Category());
		return "admin/addCategory";
	}
	
	@PostMapping("/admin/categories/add")
		public String adminAddCategoryPost(@ModelAttribute Category category) throws IOException, InterruptedException {
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/edit/{id}")
		public String adminUpdateCategoryGet(Model model, @PathVariable Long id) throws IOException, InterruptedException {
		model.addAttribute("category", categoryService.getCategoryById(id));
		return "admin/addCategory";
	}
	
	@PostMapping("/admin/categories/edit/{id}")
		public String adminUpdateCategoryPost(@ModelAttribute Category category, @PathVariable Long id) throws IOException, InterruptedException {
		categoryService.updateCategory(category);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/delete/{id}")
		public String adminDeleteCategory(@PathVariable Long id) throws IOException, InterruptedException {
		categoryService.deleteCategoryById(id);
		return "redirect:/admin/categories";
	}

	
//  ------------------------ Function call http request ------------------------  //

//	List<Category> getAllCategory() throws IOException, InterruptedException {
//		HttpClient clientCategory = HttpClient.newHttpClient();
//		HttpRequest requestCategory = HttpRequest
//			.newBuilder()
//			.uri(URI.create("http://localhost:8080/apis/categories"))
//			.build();
//		HttpResponse<String> response = clientCategory.send(
//			requestCategory,
//			HttpResponse.BodyHandlers.ofString()
//		);
//		System.out.println(response);
//		String jsonStr = response.body();
//		System.out.println(jsonStr);
//		ObjectMapper objectMapper = new ObjectMapper();
//		List<Category> categoryList = objectMapper.readValue(
//			jsonStr,
//			new TypeReference<List<Category>>() {}
//		);
//		System.out.println(categoryList);
//		return categoryList;
//	}
//
//	void addCategory(Category category) throws IOException, InterruptedException {
//		String requestBodyProduct = new ObjectMapper().writeValueAsString(category);
//		System.out.println(requestBodyProduct);
//		HttpClient clientProduct = HttpClient.newHttpClient();
//		HttpRequest requestProduct = HttpRequest
//			.newBuilder()
//			.header("content-type", "application/json")
//			.uri(URI.create("http://localhost:8080/apis/categories"))
//			.POST(HttpRequest.BodyPublishers.ofString(requestBodyProduct))
//			.build();
//
//		HttpResponse<String> responseProduct = clientProduct.send(
//			requestProduct,
//			HttpResponse.BodyHandlers.ofString()
//		);
//	}
//
//	void updateCategory(Category category, Long id)  throws IOException, InterruptedException {
//		String requestBodyProduct = new ObjectMapper().writeValueAsString(category);
//		System.out.println(requestBodyProduct);
//		HttpClient clientProduct = HttpClient.newHttpClient();
//		HttpRequest requestProduct = HttpRequest
//			.newBuilder()
//			.header("content-type", "application/json")
//			.uri(URI.create("http://localhost:8080/apis/category/" + id))
//			.PUT(HttpRequest.BodyPublishers.ofString(requestBodyProduct))
//			.build();
//
//		HttpResponse<String> responseProduct = clientProduct.send(
//			requestProduct,
//			HttpResponse.BodyHandlers.ofString()
//		);
//	}
//
//	void deleteCategory(Long id) throws IOException, InterruptedException {
//		HttpClient client = HttpClient.newHttpClient();
//		HttpRequest request = HttpRequest
//			.newBuilder()
//			.header("content-type", "application/json")
//			.uri(URI.create("http://localhost:8080/apis/category/" + id))
//			.DELETE()
//			.build();
//
//		HttpResponse<String> response = client.send(
//			request,
//			HttpResponse.BodyHandlers.ofString()
//		);
//	}
//
//	Category getCategoryById(Long id) throws IOException, InterruptedException {
//		HttpClient clientCategory = HttpClient.newHttpClient();
//		HttpRequest requestCategory = HttpRequest
//			.newBuilder()
//			.uri(URI.create("http://localhost:8080/apis/category/" + id))
//			.build();
//		HttpResponse<String> response = clientCategory.send(
//			requestCategory,
//			HttpResponse.BodyHandlers.ofString()
//		);
//		String jsonStr = response.body();
//		ObjectMapper objectMapper = new ObjectMapper();
//		Category category = objectMapper.readValue(
//			jsonStr,
//			Category.class
//		);
//		return category;
//	}
//
//	List<Product> getAllProduct() throws IOException, InterruptedException {
//		HttpClient client = HttpClient.newHttpClient();
//		HttpRequest requestCategory = HttpRequest
//			.newBuilder()
//			.uri(URI.create("http://localhost:8080/apis/products"))
//			.build();
//		HttpResponse<String> response = client.send(
//			requestCategory,
//			HttpResponse.BodyHandlers.ofString()
//		);
//		String jsonStr = response.body();
//		ObjectMapper objectMapper = new ObjectMapper();
//		List<Product> productList = objectMapper.readValue(
//			jsonStr,
//			new TypeReference<List<Product>>() {}
//		);
//		return productList;
//	}
//
//	void addProduct(Product product) throws IOException, InterruptedException {
//		String requestBody = new ObjectMapper().writeValueAsString(product);
//		System.out.println(requestBody);
//		HttpClient client = HttpClient.newHttpClient();
//		HttpRequest request = HttpRequest
//			.newBuilder()
//			.header("content-type", "application/json")
//			.uri(URI.create("http://localhost:8080/apis/products"))
//			.POST(HttpRequest.BodyPublishers.ofString(requestBody))
//			.build();
//
//		HttpResponse<String> response = client.send(
//			request,
//			HttpResponse.BodyHandlers.ofString()
//		);
//		System.out.println(response.body());
//
//	}
//
//	Product getProductById(Long id) throws IOException, InterruptedException {
//		HttpClient client = HttpClient.newHttpClient();
//		HttpRequest request = HttpRequest
//			.newBuilder()
//			.uri(URI.create("http://localhost:8080/apis/products/" + id))
//			.build();
//		HttpResponse<String> response = client.send(
//			request,
//			HttpResponse.BodyHandlers.ofString()
//		);
//		String jsonStr = response.body();
//		ObjectMapper objectMapper = new ObjectMapper();
//		Product product = objectMapper.readValue(
//			jsonStr,
//			Product.class
//		);
//		return product;
//	}
//
//	void updateProduct(Product product, Long id) throws IOException, InterruptedException {
//		String requestBodyProduct = new ObjectMapper().writeValueAsString(product);
//		System.out.println(requestBodyProduct);
//		HttpClient clientProduct = HttpClient.newHttpClient();
//		HttpRequest requestProduct = HttpRequest
//			.newBuilder()
//			.header("content-type", "application/json")
//			.uri(URI.create("http://localhost:8080/apis/products/" + id))
//			.PUT(HttpRequest.BodyPublishers.ofString(requestBodyProduct))
//			.build();
//
//		HttpResponse<String> responseProduct = clientProduct.send(
//			requestProduct,
//			HttpResponse.BodyHandlers.ofString()
//		);
//	}
//
//	void deleteProduct(Long id) throws IOException, InterruptedException {
//		HttpClient client = HttpClient.newHttpClient();
//		HttpRequest request = HttpRequest
//			.newBuilder()
//			.header("content-type", "application/json")
//			.uri(URI.create("http://localhost:8080/apis/products/" + id))
//			.DELETE()
//			.build();
//
//		HttpResponse<String> response = client.send(
//			request,
//			HttpResponse.BodyHandlers.ofString()
//		);
//	}
}
