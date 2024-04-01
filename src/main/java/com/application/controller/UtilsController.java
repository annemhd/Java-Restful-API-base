package com.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.model.Users;
import com.application.repository.UsersRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class UtilsController {
    @Autowired

    private UsersRepository usersRepository;

    @PostMapping(path = "/check-email", consumes = { "*/*" })
    public boolean checkEmails(@ModelAttribute Users body) {
        if (!usersRepository.existsByEmail(body.getEmail())) {
            return false;
        } else {
            return true;
        }
    }

    @PostMapping(path = "/check-username", consumes = { "*/*" })
    public boolean checkUsernames(@ModelAttribute Users body) {
        if (!usersRepository.existsByUsername(body.getUsername())) {
            return false;
        } else {
            return true;
        }
    }
}
