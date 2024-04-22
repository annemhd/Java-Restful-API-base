package com.application.httpmodel;

import com.application.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record HttpUser(
    Integer id,
    String username,
    String name,
    String email,
    @JsonProperty("created_at") LocalDateTime createdAt) {

  // Use a mapper like mapstruct instead
  public static HttpUser fromUser(User user) {
    return new HttpUser(user.getId(), user.getUsername(), user.getName(), user.getEmail(), user.getCreatedAt());
  }
}
