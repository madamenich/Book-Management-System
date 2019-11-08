package com.example.bms.repository;

import com.example.bms.entity.Book;
import com.example.bms.entity.Category;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class CategoryRepository {
    @PersistenceContext
    EntityManager entityManager;

    //to find all
   public List<Category> findAllCategory(){
      Query query = entityManager.createNamedQuery("findAllCategory");
       List<Category> categoryList = query.getResultList();
       return categoryList;
   }

   // to insert
   public void insertCategory(Category category){
       entityManager.persist(category);
   }

    // to update
   public void updateCategory(Integer id,Category category){
       Query query = entityManager.createNamedQuery("updateCategory");
       query.setParameter("title",category.getTitle())
               .setParameter("id",id);
       query.executeUpdate();

   }

   //to find by id
   public Category findOne(Integer id){
       return entityManager.find(Category.class,id);
   }
   //to delete
    public void delete(Integer id){
       Query query = entityManager.createQuery("DELETE Book b WHERE b.category.id=:id");
       query.setParameter("id",id);
       query.executeUpdate();
       entityManager.remove(findOne(id));
    }

}
