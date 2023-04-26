package com.application.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.model.user.Auth;
import com.application.repository.user.AuthRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(path = "/API")
public class AuthController {
    @Autowired

    private AuthRepository authRepository;

    @GetMapping(path = "/current-user")
    public @ResponseBody Iterable<Auth> getAllUsers() {
        return authRepository.findAll();
    }

    @PostMapping(path = "/current-user/create", consumes = { "*/*" })
    public Auth saveCurrentUser(@ModelAttribute Auth body) {
        authRepository.save(body);
        return body;
    }

    @DeleteMapping(path = "/current-user/destroy", consumes = { "*/*" })
    public String deleteCurrentUser(@ModelAttribute Auth id) {
        authRepository.deleteAll();
        return "L'utilisateur a bien été supprimé";
    }
}