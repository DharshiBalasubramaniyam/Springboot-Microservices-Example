package com.blog.categoryservice.services;


import com.blog.categoryservice.dtos.ApiResponseDto;
import com.blog.categoryservice.dtos.CategoryRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {

    ResponseEntity<ApiResponseDto<?>> getAllCategories();

    ResponseEntity<ApiResponseDto<?>> getCategoryById(String categoryId);

    ResponseEntity<ApiResponseDto<?>> createCategory(CategoryRequestDto categoryRequestDto);
}
