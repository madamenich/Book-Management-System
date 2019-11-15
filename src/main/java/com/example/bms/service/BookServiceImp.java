package com.example.bms.service;

import com.example.bms.entity.Book;
import com.example.bms.entity.Category;
import com.example.bms.repository.BookRepository;
import com.example.bms.repository.CategoryRepository;
import com.example.bms.service.bookService.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImp implements BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public void insert(Book book) {
        bookRepository.save(book);
    }



    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book findBookById(Integer id) {
        return bookRepository.getOne(id);
    }

    @Override
    public void update(Integer id, Book book) {
        bookRepository.save(book);
    }

    @Override
    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }




    //TO DO: to check on search and Filter
    @Override
    public Page<Book> searchByTitleAndCategoryName(Book book,Pageable pageable) {
        //TO DO: if only filter by category is used
        if(book.getTitle()==""){
            return bookRepository.findByCategoryId(book.getCategory().getId(),pageable);

        }
        //TO DO: if the category is not filtered
        else if(book.getCategory()==null){
            return  bookRepository.findByNameFree(book.getTitle(),pageable);
        }
        //TO DO: when both search by tile and filter by category are used
        else {
            return bookRepository.findByNameFreeAndCategory(book.getTitle(),book.getCategory(),pageable);
        }

    }



}
