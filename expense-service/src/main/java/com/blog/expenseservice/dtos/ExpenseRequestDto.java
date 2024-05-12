package com.blog.expenseservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ExpenseRequestDto {
    private String description;
    private double amount;
    private LocalDate date;
    private String categoryId;
}
