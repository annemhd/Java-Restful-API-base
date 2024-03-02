package com.application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.model.Users;
import com.application.repository.UsersRepository;
import com.application.utils.EmailValidator;
import com.application.utils.MD5PasswordHasher;
import com.application.utils.PasswordValidator;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class UsersController {
    @Autowired

    private UsersRepository userRepository;

    @GetMapping(path = "/users")
    public ResponseEntity<?> getAllUsers() {
        try {
            return ResponseEntity.ok(userRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur de serveur");
        }

    }

    @GetMapping(path = "/user/{id}")
    public @ResponseBody Optional<Users> getUser(@PathVariable Integer id) {
        return userRepository.findById(id);
    }

    @PostMapping(path = "/user", consumes = { "*/*" })
    public ResponseEntity<?> addNewUser(@ModelAttribute Users body) {
        try {
            if (PasswordValidator.isValidPassword(body.getPassword())
                    && EmailValidator.isValidEmail(body.getEmail())
                    && body.getUsername() != null
                    && body.getName() != null) {
                body.setPassword(MD5PasswordHasher.hashPassword(body.getPassword()));
                userRepository.save(body);
                return ResponseEntity.ok(body);
            } else {
                return ResponseEntity.status(400).body("Un ou plusieurs champs sont vides ou invalides");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur de serveur");
        }
    }

    @PatchMapping(path = "/user/{id}/update", consumes = { "*/*" })
    public Users updateUser(@PathVariable Integer id, @ModelAttribute Users body) {
        Users user = userRepository.findById(id).get();
        user.setUsername(body.getUsername());
        user.setName(body.getName());
        user.setEmail(body.getEmail());
        if (body.getPassword() != null) {
            user.setPassword(body.getPassword());
        }
        userRepository.save(user);
        return user;
    }

    @DeleteMapping(path = "/user/{id}/delete")
    public @ResponseBody String delUser(@PathVariable Integer id) {
        Users user = userRepository.findById(id).get();
        userRepository.delete(user);
        return "L'utilisateur a bien été supprimé";
    }
}