package com.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.application.model.Auth;

public interface AuthRepository extends CrudRepository<Auth, Integer> {
    @Query(value = "SELECT * FROM user WHERE email = ?", nativeQuery = true)
    Auth findByEmail(String email);

    List<Auth> findByPassword(String password);

    @Query(value = "INSERT INTO current (firstname, lastname, email, password) VALUES (?, ?, ?, ?)", nativeQuery = true)
    List<Auth> save(String firstname, String lastname, String email, String password);
}