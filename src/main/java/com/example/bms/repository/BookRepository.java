package com.example.bms.repository;

import com.example.bms.entity.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;

@Repository
public class BookRepository {
@PersistenceContext
    EntityManager entityManager;


    public List<Book> findAllBook(){
     Query query = entityManager.createNamedQuery("findAllBooks");
     List<Book> bookList = query.getResultList();
     return bookList;

 }


    public void insertBook(Book book){
        entityManager.persist(book);

    }


    public Book findBookById(Integer id){

     return entityManager.find(Book.class,id);
  }


    public void update(Integer id,Book book){

Query query = entityManager.createNamedQuery("updateBook");
query.setParameter("title",book.getTitle())
        .setParameter("author",book.getAuthor())
        .setParameter("description",book.getDescription())
        .setParameter("category_id",book.getCategory())
        .setParameter("id",id);

query.executeUpdate();
  }


    public void delete(Integer id){

     entityManager.remove(findBookById(id));
  }
  public List<Book> searchByTitle(String title){
        Query query = entityManager.createNamedQuery("filter");
        query.setParameter(1,"%"+title+"%");


        return query.getResultList();
  }
}
