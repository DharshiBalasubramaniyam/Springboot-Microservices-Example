package com.blog.expenseservice.services;

import com.blog.expenseservice.dtos.ApiResponseDto;
import com.blog.expenseservice.dtos.ExpenseRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ExpenseService {


    ResponseEntity<ApiResponseDto<?>> addExpense(ExpenseRequestDto requestDto);

    ResponseEntity<ApiResponseDto<?>> getAllExpenses();

}
