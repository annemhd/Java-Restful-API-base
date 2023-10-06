package com.application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.model.Users;
import com.application.repository.UsersRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(path = "/API")
public class UsersController {
    @Autowired

    private UsersRepository userRepository;

    @GetMapping(path = "/users")
    public @ResponseBody Iterable<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/user/{id}")
    public @ResponseBody Optional<Users> getUser(@PathVariable Integer id) {
        return userRepository.findById(id);
    }

    @PostMapping(path = "/user", consumes = { "*/*" })
    public Users addNewUser(@ModelAttribute Users body) {
        userRepository.save(body);
        return body;
    }

    @PatchMapping(path = "/user/{id}/update", consumes = { "*/*" })
    public Users updateUser(@PathVariable(value = "id") Integer id, @ModelAttribute Users body) {
        Users user = userRepository.findById(id).get();
        user.setUsername(body.getUsername());
        user.setFirstname(body.getFirstname());
        user.setLastname(body.getLastname());
        user.setEmail(body.getEmail());
        if (body.getPassword() != null) {
            user.setPassword(body.getPassword());
        }
        userRepository.save(user);
        return user;
    }

    @DeleteMapping(path = "/user/{id}/delete")
    public @ResponseBody String delUser(@PathVariable(value = "id") Integer id) {
        Users user = userRepository.findById(id).get();
        userRepository.delete(user);
        return "L'utilisateur a bien été supprimé";
    }
}