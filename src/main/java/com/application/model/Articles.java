package com.application.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Articles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_article", updatable = false, nullable = false)
    private Integer id_article;

    private Integer id_user;

    private String title;

    private String description;

    private String price;

    private String status;

    private LocalDate createdAt = LocalDate.now();

    public Integer getIdArticle() {
        return id_article;
    }

    public void setIdArticle(Integer id_article) {
        this.id_article = id_article;
    }

    public Integer getIdUser() {
        return id_user;
    }

    public void setIdUser(Integer id_user) {
        this.id_user = id_user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}