package com.application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.model.User;
import com.application.repository.UserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "/API")
public class MainController {
    @Autowired

    private UserRepository userRepository;

    @GetMapping(path = "/user")
    public @ResponseBody Iterable<User> getAllUsers() {

        return userRepository.findAll();
    }

    @GetMapping(path = "/user/{id}")
    public @ResponseBody Optional<User> getUser(@PathVariable Integer id) {

        return userRepository.findById(id);
    }

    @PostMapping(path = "/user")
    public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email) {
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Le nouvelle utilisateur a bien été sauvegardé";
    }

    @PostMapping(path = "/user/{id}/update")
    public @ResponseBody String updateUser(@PathVariable Iterable<Integer> id, @RequestParam String name,
            @RequestParam String email) {
        User updateData = (User) userRepository.findAllById(id);
        updateData.setName(name);
        userRepository.save(updateData);
        return "L'utilisateur a bien été mis à jour";
    }

    @PostMapping(path = "/user/{id}/delete")
    public @ResponseBody String delUser(@RequestParam Integer id) {
        userRepository.deleteById(id);
        return "L'utilisateur a bien été supprimé";
    }
}