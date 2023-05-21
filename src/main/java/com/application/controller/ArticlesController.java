package com.application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.model.Articles;
import com.application.repository.ArticlesRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(path = "/API")
public class ArticlesController {
    @Autowired

    private ArticlesRepository articlesRepository;

    @GetMapping(path = "/articles")
    public @ResponseBody Iterable<Articles> getAllArticles() {
        return articlesRepository.findAll();
    }

    @GetMapping(path = "/article/{id}")
    public @ResponseBody Optional<Articles> getArticle(@PathVariable Integer id) {
        return articlesRepository.findById(id);
    }

    @PostMapping(path = "/article", consumes = { "*/*" })
    public Articles addNewUser(@ModelAttribute Articles body) {
        articlesRepository.save(body);
        return body;
    }

    @PutMapping(path = "/article/{id}/update", consumes = { "*/*" })
    public Articles updateUser(@PathVariable(value = "id") Integer id, @ModelAttribute Articles body) {
        Articles article = articlesRepository.findById(id).get();
        article.setTitle(body.getTitle());
        article.setDescription(body.getDescription());
        article.setPrice(body.getPrice());
        article.setStatus(body.getStatus());
        articlesRepository.save(article);
        return article;
    }

    @DeleteMapping(path = "/article/{id}/delete")
    public @ResponseBody String delUser(@PathVariable(value = "id") Integer id) {
        Articles article = articlesRepository.findById(id).get();
        articlesRepository.delete(article);
        return "L'article a bien été supprimé";
    }
}
