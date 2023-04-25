package com.application.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.application.model.user.Auth;

public interface AuthRepository extends CrudRepository<Auth, Integer> {
    @Query(value = "SELECT * FROM user WHERE email = ?", nativeQuery = true)
    List<Auth> findByEmail(String email);

    List<Auth> findByPassword(String password);

    @Query(value = "INSERT INTO current (firstname, lastname, email) VALUES (?, ?, ?, ?)", nativeQuery = true)
    List<Auth> save(String firstname, String lastname, String email, String password);
}