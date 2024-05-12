package com.blog.categoryservice.controllers;

import com.blog.categoryservice.dtos.ApiResponseDto;
import com.blog.categoryservice.dtos.CategoryRequestDto;
import com.blog.categoryservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/new")
    public ResponseEntity<ApiResponseDto<?>> createCategory(@RequestBody CategoryRequestDto categoryRequestDto){
        return categoryService.createCategory(categoryRequestDto);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponseDto<?>> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<?>> getCategoryById(@PathVariable String id) {
        return categoryService.getCategoryById(id);
    }

}
