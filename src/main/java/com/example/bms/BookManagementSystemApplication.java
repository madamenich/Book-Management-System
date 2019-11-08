package com.example.bms;

import com.example.bms.entity.Book;
import com.example.bms.entity.Category;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootApplication
@Transactional
public class BookManagementSystemApplication implements CommandLineRunner {
@PersistenceContext
	EntityManager entityManager;
	public static void main(String[] args) {
		SpringApplication.run(BookManagementSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		addCategory();

		for(int i= 0;i<4;i++){
			entityManager.persist(new Book("To the boy I have loved","Madame","It was just a good day ",new Category(3)));
			entityManager.persist(new Book("Panda Panda","Irene","It's just a normal book too",new Category(2)));
			entityManager.persist(new Book("Suppose to be happy","I am unknown Author","Love is blind",new Category(1)));

		}
	}

	public  void addCategory(){
		Category category1 = new Category("Comedy");
		Category category2 = new Category("Comic");
		Category category3= new Category("Love");
		entityManager.persist(category1);
		entityManager.persist(category2);
		entityManager.persist(category3);


	}
	
}
