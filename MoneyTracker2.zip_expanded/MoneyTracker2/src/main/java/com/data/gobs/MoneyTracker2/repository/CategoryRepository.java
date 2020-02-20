package com.data.gobs.MoneyTracker2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.gobs.MoneyTracker2.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	Category findBycatName(String catName);
}
