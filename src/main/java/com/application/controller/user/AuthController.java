package com.application.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.model.user.User;
import com.application.repository.user.AuthRepository;
import com.application.repository.user.UserRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(path = "/API")
public class AuthController {
    @Autowired

    private UserRepository userRepository;
    private AuthRepository authRepository;

    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    @PostMapping(path = "/auth", consumes = { "*/*" })
    public String authUser(@RequestParam String email, @RequestParam String password) {
        User user = userRepository.findByEmail(email).get(0);
        String pwd = MD5(password);
        String pwdUser = user.getPassword();
        if (pwd.equals(pwdUser)) {
            authRepository.save(user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword());
            return "Auth !";
        } else {
            return "Nope !";
        }
    }
}