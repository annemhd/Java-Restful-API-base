package com.application.services;

import com.application.model.User;
import com.application.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class UserServiceTest {

  @Autowired
  private UsersRepository repository;

  private UserService userService;

  @BeforeEach
  public void init() {
    this.userService = new UserService(repository);
  }

  @Test
  public void testGetAllUsers() {
    List<User> all = userService.findAll(Sort.unsorted());

    assertEquals(1, all.size());
  }

  // Add more tests here

}