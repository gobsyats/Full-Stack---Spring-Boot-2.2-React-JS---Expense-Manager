package com.data.gobs.MoneyTracker2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.gobs.MoneyTracker2.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
