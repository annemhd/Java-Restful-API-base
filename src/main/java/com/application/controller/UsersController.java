package com.application.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.model.Users;
import com.application.repository.UsersRepository;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired

    private UsersRepository usersRepository;

    @GetMapping(path = "/get")
    public ResponseEntity<?> getUsers() {
        Iterable<Users> users = usersRepository.findAll();

        if (!users.iterator().hasNext()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Pas d'utilisateurs trouvés");
        } else {
            List<Map<String, String>> userList = new ArrayList<>();

            for (Users user : users) {
                Map<String, String> userMap = new HashMap<>();
                userMap.put("username", user.getUsername());
                userMap.put("name", user.getName());
                userMap.put("email", user.getEmail());
                userMap.put("created_at", user.getCreated_at().toString());
                userList.add(userMap);
            }

            return ResponseEntity.ok(userList);
        }
    }

    @GetMapping(path = "/{id}/get")
    public ResponseEntity<?> getUser(@PathVariable("id") int id) {
        Optional<Users> optionalUser = usersRepository.findById(id);

        if (optionalUser.isPresent()) {
            Users existingUser = optionalUser.get();

            List<Map<String, String>> userFound = new ArrayList<>();

            Map<String, String> userMap = new HashMap<>();
            userMap.put("username", existingUser.getUsername());
            userMap.put("name", existingUser.getName());
            userMap.put("email", existingUser.getEmail());
            userMap.put("created_at", existingUser.getCreated_at().toString());
            userFound.add(userMap);

            return ResponseEntity.ok(userFound);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("L'utilisateur est introuvable");
        }
    }

    @PostMapping(path = "/create")
    public Map<String, Object> createUser(@RequestBody @Validated Users request) {
        Users createdUser = usersRepository.save(request);

        return Map.of("username", createdUser.getUsername(), "name", createdUser.getName(),
                "email",
                createdUser.getEmail(), "created_at", createdUser.getCreated_at());
    }

    @PutMapping(path = "/{id}/update")
    public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestBody @Validated Users updatedUser) {
        Optional<Users> optionalUser = usersRepository.findById(id);

        if (optionalUser.isPresent()) {
            Users existingUser = optionalUser.get();

            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());

            if (updatedUser.getPassword() != existingUser.getPassword()) {
                existingUser.setPassword(updatedUser.getPassword());
            }

            System.out.print(existingUser.getPassword());
            System.out.print(updatedUser.getPassword());

            usersRepository.save(existingUser);

            return ResponseEntity.ok("L'utilisateur a bien été mis à jour");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("L'utilisateur est introuvable");
        }
    }

    @PutMapping(path = "/{id}/password/")
    public ResponseEntity<?> updateUserPassword(@PathVariable("id") int id, @RequestBody Users updatedUser) {
        Optional<Users> optionalUser = usersRepository.findById(id);

        if (optionalUser.isPresent()) {
            Users existingUser = optionalUser.get();

            existingUser.setPassword(updatedUser.getPassword());

            usersRepository.save(existingUser);

            return ResponseEntity.ok("Le mot de passe a bien été mis à jour");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("L'utilisateur est introuvable");
        }
    }
}
