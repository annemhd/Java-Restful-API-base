package com.application.httpmodel;

import lombok.Data;

import java.util.Optional;

@Data
public class UpdateUserRequest {

  private String username;
  private String name;
  private String email;

  public Optional<String> getUsername() {
    return Optional.ofNullable(username);
  }

  public Optional<String> getName() {
    return Optional.ofNullable(name);
  }

  public Optional<String> getEmail() {
    return Optional.ofNullable(email);
  }
}
