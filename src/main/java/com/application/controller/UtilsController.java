package com.application.controller;

import com.application.model.User;
import com.application.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UtilsController {

  private final UsersRepository usersRepository;

  @PostMapping(path = "/check-email", consumes = {"*/*"})
  public boolean checkEmails(@ModelAttribute User body) {
    return usersRepository.existsByEmail(body.getEmail());
  }

  @PostMapping(path = "/check-username", consumes = {"*/*"})
  public boolean checkUsernames(@ModelAttribute User body) {
    return usersRepository.existsByUsername(body.getUsername());
  }
}
