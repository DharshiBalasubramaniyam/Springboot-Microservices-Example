package com.blog.expenseservice.services;


import com.blog.expenseservice.dtos.ApiResponseDto;
import com.blog.expenseservice.dtos.CategoryDto;
import com.blog.expenseservice.dtos.ExpenseRequestDto;
import com.blog.expenseservice.feigns.CategoryFeignService;
import com.blog.expenseservice.modals.Expense;
import com.blog.expenseservice.repostories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private CategoryFeignService categoryFeignService;

    @Override
    public ResponseEntity<ApiResponseDto<?>> addExpense(ExpenseRequestDto requestDto) {
        try {

            // fetching category from category service
            CategoryDto category = categoryFeignService.getCategoryById(requestDto.getCategoryId()).getBody().getResponse();

            if (category == null) {
                // Try to create a custom exception and handle them using exception handlers
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        ApiResponseDto.builder()
                                .isSuccess(false)
                                .response("Category not exists with id: " + requestDto.getCategoryId())
                                .message("Unable to create Category.")
                                .build()
                );
            }

            Expense expense = Expense.builder()
                            .description(requestDto.getDescription())
                            .amount(requestDto.getAmount())
                            .date(requestDto.getDate())
                            .categoryId(requestDto.getCategoryId())
                            .build();

            expenseRepository.insert(expense);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    ApiResponseDto.builder()
                            .isSuccess(true)
                            .message("Expense saved successfully!")
                            .build()
            );

        }catch (Exception e) {
//            Try to create a custom exception and handle them using exception handlers
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ApiResponseDto.builder()
                            .isSuccess(false)
                            .response("Unable to process right now. Try again later!")
                            .message("Unable to save expense.")
                            .build()
            );
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getAllExpenses() {
        List<Expense> expenses = expenseRepository.findAll();
        try {
            return ResponseEntity.ok(
                    ApiResponseDto.builder()
                            .isSuccess(true)
                            .response(expenses)
                            .message(expenses.size() + " results found!")
                            .build()
            );
        }catch (Exception e) {
//            Try to create a custom exception and handle them using exception handlers
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ApiResponseDto.builder()
                            .isSuccess(false)
                            .response("Unable to process right now. Try again later!")
                            .message("No results found!")
                            .build()
            );
        }
    }
}
