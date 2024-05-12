package com.blog.expenseservice.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {

    private String id;

    private String categoryName;
}
