package com.example.bms.service;

import com.example.bms.entity.Category;
import com.example.bms.repository.CategoryRepository;
import com.example.bms.service.categoryService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public void insertCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(Integer id,Category category) {
        categoryRepository.save(category);
    }

  public Category findOne(Integer id){
        return  categoryRepository.getOne(id);
  }

    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }


}

