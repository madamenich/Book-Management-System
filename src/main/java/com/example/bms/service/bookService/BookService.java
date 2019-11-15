package com.example.bms.service.bookService;

import com.example.bms.entity.Book;
import com.example.bms.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    public void insert(Book book);

    public  Book findBookById(Integer id);
    public  void update(Integer id, Book book);
    public  void delete(Integer id);
    public Page<Book> searchByTitleAndCategoryName(Book book,Pageable pageable);

   public Page<Book> findAll(Pageable pageable);


}
