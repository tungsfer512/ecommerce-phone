package io.sfer.xxx.Service;

import io.sfer.xxx.Model.Category;
import io.sfer.xxx.Respository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}
	
	public Category addCategory(Category category) {
		return categoryRepository.save(category);
	}
	
	public Category getCategoryById(Long id) {
		return categoryRepository.findById(id).get();
	}
	
	public void deleteCategoryById(Long id) {
		categoryRepository.deleteById(id);
	}
	
	public Category updateCategory(Category category) {
		return categoryRepository
			.findById(category.getId())
			.map(
				existedCategory -> {
					existedCategory.setName(category.getName());
					return categoryRepository.save(existedCategory);
				}
			)
			.orElseGet(
				() -> {
					return categoryRepository.save(category);
				}
			);
	}
}
