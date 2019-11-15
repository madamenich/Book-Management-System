package com.example.bms;

import com.example.bms.entity.Book;
import com.example.bms.entity.Category;
import com.example.bms.repository.BookRepository;
import com.example.bms.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootApplication


public class BookManagementSystemApplication implements CommandLineRunner {
@Autowired
BookRepository bookRepository;
@Autowired
CategoryRepository categoryRepository;
	public static void main(String[] args) {
		SpringApplication.run(BookManagementSystemApplication.class, args);
	}
	@Bean
	public SpringDataDialect springDataDialect() {
		return new SpringDataDialect();
	}
	@Override
	public void run(String... args) throws Exception {
		addCategory();

		for(int i= 0;i<10;i++){
			bookRepository.save(new Book("To the boy I have loved","Madame","It was just a good day ","Default.jpg",new Category(3)));
			bookRepository.save(new Book("Panda Panda","Irene","It's just a normal book too","Default.jpg",new Category(2)));
			bookRepository.save(new Book("Suppose to be happy","I am unknown Author","Love is blind","Default.jpg",new Category(1)));

		}
//		System.out.println(bookRepository.findByCategoryId("Love"));

	}

	public  void addCategory(){
	categoryRepository.save(new Category("Comedy")); ;
		categoryRepository.save(new Category("Comic")) ;
		categoryRepository.save(new Category("Love")) ;


	}

}
