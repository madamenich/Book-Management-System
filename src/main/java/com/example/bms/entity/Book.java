package com.example.bms.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_books")

        @NamedQuery(name = "findAllBooks",query = "SELECT b FROM Book b")
        @NamedQuery(name = "updateBook",query = "UPDATE Book b SET b.title =:title, b.author=:author ,b.description=:description,b.category=:category_id WHERE b.id= :id")
        @NamedQuery(name = "filter",query = "SELECT  b FROM Book b WHERE b.title LIKE ?1")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

    private String title;

    private  String author;

    private String description;
    @ManyToOne
    Category category;

    public Book() {
    }

    public Book(String title, String author, String description, Category category) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.category = category;
    }

    public Book(Integer id, String title, String author, String description, Category category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                '}';
    }
}
