package com.application.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.application.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query(value = "SELECT * FROM user WHERE email = ? AND password = ?", nativeQuery = true)
    UserRepository auth(String email, String password);
}