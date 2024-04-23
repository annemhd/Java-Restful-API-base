package com.application.controller;

import com.application.model.Article;
import com.application.repository.ArticlesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ArticlesController {

  private final ArticlesRepository articlesRepository;

  @GetMapping(path = "/articles")
  public Iterable<Article> getAllArticles(Sort sort) {
    return articlesRepository.findAll(sort);
  }

  @GetMapping(path = "/article/{id}")
  public Optional<Article> getArticle(@PathVariable Integer id) {
    return articlesRepository.findById(id);
  }

  @PostMapping(path = "/article")
  public Article addNewArticle(@RequestBody Article body) {
    System.out.println(body);
    articlesRepository.save(body);
    return body;
  }

  @PutMapping(path = "/article/{id}")
  public Article updateArticle(@PathVariable Integer id, @RequestBody Article body) {
    Article article = articlesRepository.findById(id).get();
    article.setTitle(body.getTitle());
    article.setDescription(body.getDescription());
    article.setPrice(body.getPrice());
    article.setStatus(body.getStatus());
    articlesRepository.save(article);
    return article;
  }

  @DeleteMapping(path = "/article/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteArticle(@PathVariable Integer id) {
    Article article = articlesRepository.findById(id).get();
    articlesRepository.delete(article);
  }
}
