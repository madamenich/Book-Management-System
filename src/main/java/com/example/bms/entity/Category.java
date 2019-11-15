package com.example.bms.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_categories")
@NamedQuery(name = "findAllCategory",query = "SELECT c FROM Category c")
@NamedQuery(name = "updateCategory", query = "UPDATE Category c SET c.title=:title WHERE c.id=:id")

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
@NotNull
    private String title;

    public Category(Integer id) {
        this.id = id;
    }

    public Category(String title) {
        this.title = title;
    }

    public Category() {

    }

    public Category(Integer id, String title) {
        this.id = id;
        this.title = title;
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

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}

