package com.application.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_article", updatable = false, nullable = false)
    private Integer idArticle;
    @Column(name = "id_user")
    private Integer idUser;
    private String title;
    private String description;
    private String price;
    private String status;
    @CreationTimestamp
    @Column(name = "created_at")
    @EqualsAndHashCode.Exclude
    private LocalDateTime createdAt = LocalDateTime.now();
//    private List<String> images;

}