package com.example.bms.service.categoryService;

import com.example.bms.entity.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> findAllCategory();
    public void insertCategory(Category category);
    public void updateCategory(Integer id,Category category);
    public void delete(Integer id);
    public Category findOne(Integer id);

}
