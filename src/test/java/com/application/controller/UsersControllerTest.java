package com.application.controller;

import com.application.configuration.SecurityConfiguration;
import com.application.exception.UserNotFoundException;
import com.application.model.User;
import com.application.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsersController.class)
@Import(SecurityConfiguration.class)
public class UsersControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private UserService userService;

  @Test
  public void testGetAllUsers() throws Exception {

    when(userService.findAll(any()))
        .thenReturn(List.of(
            User.builder()
                .id(1)
                .name("User 1")
                .username("user_1")
                .email("user1@email.com")
                .createdAt(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build(),
            User.builder()
                .id(2)
                .name("User 2")
                .username("user_2")
                .email("user2@email.com")
                .createdAt(LocalDateTime.of(2021, 1, 1, 0, 0, 0))
                .build()));

    mvc.perform(get("/users"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id", equalTo(1)))
        .andExpect(jsonPath("$[0].username", equalTo("user_1")))
        .andExpect(jsonPath("$[0].name", equalTo("User 1")))
        .andExpect(jsonPath("$[0].email", equalTo("user1@email.com")))
        .andExpect(jsonPath("$[0].created_at", equalTo("2020-01-01T00:00:00")))
        .andExpect(jsonPath("$[0].password").doesNotExist())
        .andExpect(jsonPath("$[1].id", equalTo(2)))
        .andExpect(jsonPath("$[1].username", equalTo("user_2")))
        .andExpect(jsonPath("$[1].name", equalTo("User 2")))
        .andExpect(jsonPath("$[1].email", equalTo("user2@email.com")))
        .andExpect(jsonPath("$[1].created_at", equalTo("2021-01-01T00:00:00")))
        .andExpect(jsonPath("$[1].password").doesNotExist());
  }

  @Test
  public void testGetUser_notFound() throws Exception {
    when(userService.findUserById(666)).thenThrow(new UserNotFoundException());

    mvc.perform(get("/users/{id}", 666))
        .andExpect(status().isNotFound());
  }

  @Test
  public void testGetUser() throws Exception {
    when(userService.findUserById(666))
        .thenReturn(User.builder()
            .id(1)
            .name("User 1")
            .username("user_1")
            .email("user1@email.com")
            .createdAt(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
            .build());

    mvc.perform(get("/users/{id}", 666))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", equalTo(1)))
        .andExpect(jsonPath("$.username", equalTo("user_1")))
        .andExpect(jsonPath("$.name", equalTo("User 1")))
        .andExpect(jsonPath("$.email", equalTo("user1@email.com")))
        .andExpect(jsonPath("$.created_at", equalTo("2020-01-01T00:00:00")))
        .andExpect(jsonPath("$.password").doesNotExist());
  }

  @Test
  public void testCreateUser() throws Exception {

    when(userService.createUser("haxx0r", "jeanmichel", "jean@michel.com", "aaS12cc_d"))
        .thenReturn(User.builder()
            .id(666)
            .name("jeanmichel")
            .username("haxx0r")
            .email("jean@michel.com")
            .password("hashed_password")
            .createdAt(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
            .build());

    mvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {
                 "username": "haxx0r",
                 "name": "jeanmichel",
                 "email": "jean@michel.com",
                 "password": "aaS12cc_d"
                }"""))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id", equalTo(666)))
        .andExpect(jsonPath("$.username", equalTo("haxx0r")))
        .andExpect(jsonPath("$.name", equalTo("jeanmichel")))
        .andExpect(jsonPath("$.email", equalTo("jean@michel.com")))
        .andExpect(jsonPath("$.created_at", equalTo("2020-01-01T00:00:00")))
        .andExpect(jsonPath("$.password").doesNotExist());
  }

  @Test
  public void testCreateUser_validation() throws Exception {

    mvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {
                 "username": "haxx0r",
                 "name": "jean-michel",
                 "email": "jean@michel.com",
                 "password": "aaS12cc_d"
                }"""))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.name", equalTo("must match \"^[a-zA-Z0-9]+$\"")));

    mvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {
                 "username": "haxx0r",
                 "name": "jeanmichel",
                 "email": "not an email",
                 "password": "aaS12cc_d"
                }"""))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.email", equalTo("L'adresse mail est invalide")));

    verifyNoInteractions(userService);
  }

  // Test others paths
}