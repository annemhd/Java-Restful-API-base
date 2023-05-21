package com.application.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.application.model.Users;

public interface UsersRepository extends CrudRepository<Users, Integer> {
    List<Users> findByEmail(String email);

    List<Users> findByPassword(String password);
}