package com.blog.categoryservice.services;

import com.blog.categoryservice.dtos.ApiResponseDto;
import com.blog.categoryservice.dtos.CategoryRequestDto;
import com.blog.categoryservice.modals.Category;
import com.blog.categoryservice.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<ApiResponseDto<?>> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        try {
            return ResponseEntity.ok(
                    ApiResponseDto.builder()
                            .isSuccess(true)
                            .response(categories)
                            .message(categories.size() + " results found!")
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

    @Override
    public ResponseEntity<ApiResponseDto<?>> getCategoryById(String categoryId) {

        try {
            Category category = categoryRepository.findById(categoryId).orElse(null);
            return ResponseEntity.ok(
                    ApiResponseDto.builder()
                            .isSuccess(true)
                            .response(category)
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

    @Override
    public ResponseEntity<ApiResponseDto<?>> createCategory(CategoryRequestDto categoryRequestDto) {
        try {
            if (categoryRepository.existsByCategoryName(categoryRequestDto.getName())) {
                // Try to create a custom exception and handle them using exception handlers
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        ApiResponseDto.builder()
                                .isSuccess(false)
                                .response("Category name already exists: " + categoryRequestDto.getName())
                                .message("Unable to create Category.")
                                .build()
                );
            }

            Category category = Category.builder()
                    .categoryName(categoryRequestDto.getName())
                    .build();

            categoryRepository.insert(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    ApiResponseDto.builder()
                        .isSuccess(true)
                        .message("Category saved successfully!")
                        .build()
            );

        }catch (Exception e) {
//            Try to create a custom exception and handle them using exception handlers
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ApiResponseDto.builder()
                            .isSuccess(false)
                            .response("Unable to process right now. Try again later!")
                            .message("Unable to create Category.")
                            .build()
            );
        }
    }


}
