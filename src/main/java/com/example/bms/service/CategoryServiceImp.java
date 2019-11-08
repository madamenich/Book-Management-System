package com.example.bms.service;

import com.example.bms.entity.Category;
import com.example.bms.repository.CategoryRepository;
import com.example.bms.service.categoryService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAllCategory();
    }

    @Override
    public void insertCategory(Category category) {
        categoryRepository.insertCategory(category);
    }

    @Override
    public void updateCategory(Integer id,Category category) {
        categoryRepository.updateCategory(id,category);
    }

    @Override
    public Category findOne(Integer id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.delete(id);
    }
}
