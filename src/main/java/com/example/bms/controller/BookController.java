package com.example.bms.controller;

import com.example.bms.entity.Book;
import com.example.bms.entity.Category;
import com.example.bms.service.bookService.BookService;
import com.example.bms.service.categoryService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller

@Transactional
public class BookController {
@Autowired
    BookService bookService;
@Autowired
    CategoryService categoryService;
@PersistenceContext
    EntityManager entityManager;


    @GetMapping("/")

    public String displayAll(@ModelAttribute("Book") Book book,ModelMap modelMap){
        System.out.println(book);
        List<Book> bookList=null;
        modelMap.addAttribute("book",book);
        if(book.getTitle()!=null){
           bookList = bookService.searchByTitle(book.getTitle());
            System.out.println(book.getTitle());
        }
    else {
            bookList = bookService.findAllBook();
            System.out.println(bookList);
        }
        modelMap.addAttribute("category",categoryService.findAllCategory());
        modelMap.addAttribute("books", bookList);
       return "index" ;
    }


    @GetMapping("/add")
    public String showFormAddBook(ModelMap modelMap){
        Book book = new Book();
        List<Category> categoryList =categoryService.findAllCategory();
        modelMap.addAttribute("categories",categoryList);
        modelMap.addAttribute("book",book);

         return "add-new-book";
    }

    @PostMapping("/add")
    public String addNewBook(@Valid @ModelAttribute("Book") Book book, BindingResult bindingResult,ModelMap modelMap){
        if(bindingResult.hasErrors()){
            modelMap.addAttribute("book",book);
            modelMap.addAttribute("categories",categoryService.findAllCategory());
            return "add-new-book";
        }
        bookService.insert(book);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String showFormUpdateBook(ModelMap modelMap,@PathVariable("id") Integer id){
        modelMap.addAttribute("book",bookService.findBookById(id));
        modelMap.addAttribute("categories",categoryService.findAllCategory());
        return "update-book";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@Valid @ModelAttribute("book") Book book,BindingResult bindingResult,@PathVariable("id") Integer id,
                             ModelMap modelMap){
        if(bindingResult.hasErrors()){
            modelMap.addAttribute("categories",categoryService.findAllCategory());
            return "update-book";
        }
        bookService.update(id,book);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){

        bookService.delete(id);
        return "redirect:/";

    }
    @GetMapping("/view/{id}")
    public String viewOneBook(@PathVariable("id") Integer id,ModelMap modelMap){
        modelMap.addAttribute("book",bookService.findBookById(id));
        return "view-one-book";
    }

}
