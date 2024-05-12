package com.blog.expenseservice.modals;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "expenses")
@Data
@Builder
public class Expense {

    @Id
    private String id;
    private String description;
    private double amount;
    private LocalDate date;
    private String categoryId;

}
