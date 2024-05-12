package com.blog.expenseservice.controllers;

import com.blog.expenseservice.dtos.ApiResponseDto;
import com.blog.expenseservice.dtos.ExpenseRequestDto;
import com.blog.expenseservice.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/new")
    public ResponseEntity<ApiResponseDto<?>> addExpense(@RequestBody ExpenseRequestDto requestDto){
        return expenseService.addExpense(requestDto);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponseDto<?>> getAllExpenses(){
        return expenseService.getAllExpenses();
    }


}
