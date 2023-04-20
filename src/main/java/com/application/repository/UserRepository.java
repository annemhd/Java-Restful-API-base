package com.application.repository;

import org.springframework.data.repository.CrudRepository;

import com.application.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}