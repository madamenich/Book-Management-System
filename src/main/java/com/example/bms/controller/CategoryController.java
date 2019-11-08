package com.example.bms.controller;

import com.example.bms.entity.Category;
import com.example.bms.service.categoryService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
     @Autowired
    CategoryService categoryService;
    @GetMapping("")
    public String showAllCategory(ModelMap modelMap){
        modelMap.addAttribute("categories",categoryService.findAllCategory());

        return "category-index";

    }

    @GetMapping("/add")
    public String showFormAddCategory( ModelMap modelMap){

       modelMap.addAttribute("category",new Category());
       return "add-new-category";
    }

    @PostMapping("/add")
    public String addNewCategory(@Valid @ModelAttribute Category category, BindingResult bindingResult,ModelMap modelMap){
        if(bindingResult.hasErrors()){

            modelMap.addAttribute("category",category);
            return "add-new-category";
        }
        categoryService.insertCategory(category);
        return "redirect:/category";

    }

    @GetMapping("/update/{id}")
    public String showUpdateFormCategory(@PathVariable("id") Integer id,ModelMap modelMap){
      Category   category = categoryService.findOne(id);
        modelMap.addAttribute("category",category);
        return "update-category";
    }

    @PostMapping("/update/{id}")
    public String updateCategory(@Valid @ModelAttribute Category category,BindingResult bindingResult,@PathVariable("id") Integer id, ModelMap modelMap){
        if (bindingResult.hasErrors()){
            modelMap.addAttribute("category",category);
           return "update-category";
        }
        categoryService.updateCategory(id,category);
        return "redirect:/category";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Integer id){
        categoryService.delete(id);
        return "redirect:/category";
    }

}
