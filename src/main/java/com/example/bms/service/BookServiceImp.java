package com.example.bms.service;

import com.example.bms.entity.Book;
import com.example.bms.repository.BookRepository;
import com.example.bms.service.bookService.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImp implements BookService {
    @Autowired
    BookRepository bookRepository;
    @Override
    public void insert(Book book) {
        bookRepository.insertBook(book);
    }

    @Override
    public List<Book> findAllBook() {
        return bookRepository.findAllBook();
    }

    @Override
    public Book findBookById(Integer id) {
        return bookRepository.findBookById(id);
    }

    @Override
    public void update(Integer id, Book book) {
        bookRepository.update(id,book);
    }

    @Override
    public void delete(Integer id) {
        bookRepository.delete(id);
    }

    @Override
    public List<Book> searchByTitle(String title) {
        return bookRepository.searchByTitle(title);
    }
}
