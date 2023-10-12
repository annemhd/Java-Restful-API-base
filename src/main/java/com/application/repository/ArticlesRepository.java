package com.application.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import com.application.model.Articles;

public interface ArticlesRepository extends CrudRepository<Articles, Integer> {
  @Query(value = "SELECT * FROM articles ORDER BY price ASC", nativeQuery = true)
  List<Articles> findAllSortedPriceByAsc();

  @Query(value = "SELECT * FROM articles ORDER BY price DESC", nativeQuery = true)
  List<Articles> findAllSortedPriceByDesc();

  @Query(value = "SELECT * FROM articles ORDER BY created_at ASC", nativeQuery = true)
  List<Articles> findAllSortedCreatedAtByAsc();

  @Query(value = "SELECT * FROM articles ORDER BY created_at DESC", nativeQuery = true)
  List<Articles> findAllSortedCreatedAtByDesc();
}
