package com.application.controller;

import com.application.httpmodel.CreateUserRequest;
import com.application.httpmodel.HttpUser;
import com.application.httpmodel.UpdateUserPasswordRequest;
import com.application.httpmodel.UpdateUserRequest;
import com.application.model.User;
import com.application.services.user.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

  private final UserService userService;

  @GetMapping
  public List<HttpUser> getUsers(Sort sort) {
    return userService.findAll(sort).stream()
        .map(HttpUser::fromUser)
        .toList();
  }

  @GetMapping(path = "/{id}")
  public HttpUser getUser(@PathVariable("id") int id) {
    User user = userService.findUserById(id);
    return HttpUser.fromUser(user);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public HttpUser createUser(@RequestBody @Validated CreateUserRequest request) {
    User createdUser = userService.createUser(request.username(), request.name(), request.email(), request.password());
    return HttpUser.fromUser(createdUser);
  }

  @PutMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateUser(@PathVariable("id") int id, @RequestBody @Validated UpdateUserRequest updatedUser) {
    userService.updateUser(id, updatedUser.getUsername(), updatedUser.getName(), updatedUser.getEmail());
  }

  @PatchMapping(path = "/{id}/password")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateUserPassword(@PathVariable("id") int id,
      @RequestBody @Validated UpdateUserPasswordRequest request) {
    userService.updateUserPassword(id, request.password());
  }
}
