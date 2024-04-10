package com.application.repository;

import com.application.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticlesRepository extends JpaRepository<Article, Integer> {

}
