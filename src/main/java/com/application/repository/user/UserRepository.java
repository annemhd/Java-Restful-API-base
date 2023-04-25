package com.application.repository.user;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.application.model.user.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByEmail(String email);

    List<User> findByPassword(String password);
}