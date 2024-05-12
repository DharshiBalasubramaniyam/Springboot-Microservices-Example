package com.blog.expenseservice.repostories;

import com.blog.expenseservice.modals.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends MongoRepository<Expense, String> {
}
