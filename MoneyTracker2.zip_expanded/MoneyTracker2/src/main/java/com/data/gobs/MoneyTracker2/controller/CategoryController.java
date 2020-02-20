package com.data.gobs.MoneyTracker2.controller;

import java.net.URI;

import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.gobs.MoneyTracker2.model.Category;
import com.data.gobs.MoneyTracker2.repository.CategoryRepository;

@RestController
@RequestMapping("api")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;

	
	public CategoryRepository getCategoryRepository() {
		return categoryRepository;
	}

	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@GetMapping("/categories")
	public Collection<Category> getAllCategories(){
		return categoryRepository.findAll();
	}
	
	@GetMapping("/category/{catId}")
	public ResponseEntity<?> getCategory(@PathVariable Long catId) {
		Optional<Category> category = categoryRepository.findById(catId);
		return category.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/category")
	ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) throws URISyntaxException{ 
		Category result = categoryRepository.save(category);
		return ResponseEntity.created(new URI("/api/category"+ result.getCatId())).body(result);
	}
	
	@PutMapping("/category/{catId}")
	ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category){
		Category result = categoryRepository.save(category);
		return ResponseEntity.ok().body(result);
	}
	
	@DeleteMapping("/category/{catId}")
	ResponseEntity<?> deleteCategory(@PathVariable Long catId){
		categoryRepository.deleteById(catId);
		return ResponseEntity.ok().build();	
	}
	
	
}
