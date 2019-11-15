package com.example.bms.repository;

import com.example.bms.entity.Book;
import com.example.bms.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query("select b from Book b where lower(b.title) like lower(concat('%', ?1,'%')) ")
    public Page<Book> findByNameFree(String title, org.springframework.data.domain.Pageable pageable);
    @Query("select b from Book b where lower(b.title) like lower(concat('%', ?1,'%')) and b.category = ?2")
    public  Page<Book>findByNameFreeAndCategory(String title, Category category, Pageable pageable);
   public Page<Book> findByCategoryId(Integer category, org.springframework.data.domain.Pageable pageable);

}
