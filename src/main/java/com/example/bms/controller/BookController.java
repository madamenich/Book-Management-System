package com.example.bms.controller;

import com.example.bms.entity.Book;
import com.example.bms.entity.Category;
import com.example.bms.service.bookService.BookService;
import com.example.bms.service.categoryService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller


public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    CategoryService categoryService;



    @GetMapping("/")

    public String displayAll(@ModelAttribute("Book") Book book, @PageableDefault(size = 10)  Pageable pageable, ModelMap modelMap) {



        Page<Book> page=null;
        List<Book> bookList = null;
        modelMap.addAttribute("book", book);

        modelMap.addAttribute("categories",categoryService.findAllCategory());


        if (book.getTitle()!=null||book.getCategory()!=null) {
            page =bookService.searchByTitleAndCategoryName(book,pageable);
            bookList =page.getContent();

        }

                    // TO Do: display all data by default
        else {
           page = bookService.findAll(pageable);

            bookList = page.getContent();
        }

        modelMap.addAttribute("category", categoryService.findAllCategory());

        modelMap.addAttribute("page",page);
        modelMap.addAttribute("books", bookList);


        return "index";
    }


    @GetMapping("/add")
    public String showFormAddBook(ModelMap modelMap) {
        Book book = new Book();
        List<Category> categoryList = categoryService.findAllCategory();
        modelMap.addAttribute("categories", categoryList);
        modelMap.addAttribute("book", book);

        return "add-new-book";
    }

    @PostMapping("/add")
    public String addNewBook(@Valid @ModelAttribute("Book") Book book, BindingResult bindingResult, ModelMap modelMap, @RequestParam MultipartFile file) {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("book", book);
            modelMap.addAttribute("categories", categoryService.findAllCategory());
            return "add-new-book";
        } else {
            String serverPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\image\\";
            String filename = UUID.randomUUID().toString() + "." + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".") + 1);
            if (!file.isEmpty()) {
                try {
                    Files.copy(file.getInputStream(), Paths.get(serverPath, filename));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            book.setThumbnail(filename);
            bookService.insert(book);
            return "redirect:/";
        }
    }

    @GetMapping("/update/{id}")
    public String showFormUpdateBook(ModelMap modelMap, @PathVariable("id") Integer id) {
        modelMap.addAttribute("book", bookService.findBookById(id));
        modelMap.addAttribute("categories", categoryService.findAllCategory());
        return "update-book";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, MultipartFile file, @PathVariable("id") Integer id,
                             ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("categories", categoryService.findAllCategory());
            return "update-book";
        } else {
            String serverPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\image\\";
            String filename;
            if (!file.isEmpty()) {
                filename = UUID.randomUUID().toString() + "." + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".") + 1);
                try {
                    Files.copy(file.getInputStream(), Paths.get(serverPath, filename));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                filename = book.getThumbnail();
            }


            book.setThumbnail(filename);

            bookService.update(id, book);

            return "redirect:/";
        }

    }
        @GetMapping("/view/{id}")
        public String viewOneBook (@PathVariable("id") Integer id, ModelMap modelMap){
            modelMap.addAttribute("book", bookService.findBookById(id));
            return "view-one-book";
        }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){

        bookService.delete(id);
        return "redirect:/";

    }
}
