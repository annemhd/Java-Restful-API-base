package com.application.controller.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.model.user.User;
import com.application.repository.user.UserRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(path = "/API")
public class UserController {
    @Autowired

    private UserRepository userRepository;

    @GetMapping(path = "/users")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/user/{id}")
    public @ResponseBody Optional<User> getUser(@PathVariable Integer id) {
        return userRepository.findById(id);
    }

    @PostMapping(path = "/user", consumes = { "*/*" })
    public User addNewUser(@ModelAttribute User body) {
        userRepository.save(body);
        return body;
    }

    @PutMapping(path = "/user/{id}/update", consumes = { "*/*" })
    public User updateUser(@PathVariable("id") Integer id, @ModelAttribute User body) {
        userRepository.save(body);
        return body;
    }

    @DeleteMapping(path = "/user/{id}/delete")
    public @ResponseBody String delUser(@PathVariable(value = "id") Integer id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
        return "L'utilisateur a bien été supprimé";
    }
}