package com.application.repository;

import org.springframework.data.repository.CrudRepository;
import com.application.model.Articles;

public interface ArticlesRepository extends CrudRepository<Articles, Integer> {

}
