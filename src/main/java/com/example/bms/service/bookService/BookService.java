package com.example.bms.service.bookService;

import com.example.bms.entity.Book;

import java.util.List;

public interface BookService {
    public void insert(Book book);
    public List<Book> findAllBook();
    public  Book findBookById(Integer id);
    public  void update(Integer id, Book book);
    public  void delete(Integer id);
    public List<Book> searchByTitle(String title);
}
