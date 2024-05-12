package com.blog.expenseservice.feigns;

import com.blog.expenseservice.dtos.ApiResponseDto;
import com.blog.expenseservice.dtos.CategoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("CATEGORY-SERVICE")
public interface CategoryFeignService {

    @GetMapping("/category/{id}")
    ResponseEntity<ApiResponseDto<CategoryDto>> getCategoryById(@PathVariable String id);

}
