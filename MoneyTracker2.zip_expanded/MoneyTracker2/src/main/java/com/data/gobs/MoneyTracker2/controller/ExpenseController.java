package com.data.gobs.MoneyTracker2.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.data.gobs.MoneyTracker2.model.Expense;
import com.data.gobs.MoneyTracker2.repository.ExpenseRepository;

@RestController
@RequestMapping("/api")
public class ExpenseController {
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	@GetMapping("/expenses")
	List<Expense> getExpenses(){
		return expenseRepository.findAll();
	}
	
	@DeleteMapping("/expense/{expId}")
	ResponseEntity<?> deleteExpense(@PathVariable Long expId){
		expenseRepository.deleteById(expId);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/expenses")
	ResponseEntity<Expense> createExpense(@Valid @RequestBody Expense expense) throws URISyntaxException{
		Expense result = expenseRepository.save(expense);
		return ResponseEntity.created(new URI("/api/expenses"+result.getExpId())).body(result);
	}
	
	@PutMapping("/expense/{expId}")
	ResponseEntity<Expense> updateExpense(@Valid @RequestBody Expense expense){
		Expense result = expenseRepository.save(expense);
		return ResponseEntity.ok().body(result);
	}
	
}
