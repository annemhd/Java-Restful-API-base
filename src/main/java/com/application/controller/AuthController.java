package com.application.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.h2.engine.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.model.Auth;
import com.application.repository.AuthRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(path = "/API")
public class AuthController {
    @Autowired

    private AuthRepository authRepository;

    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static String hashPwd(String mdp) {
        byte[] md5InBytes = Auth.digest(mdp.getBytes(UTF_8));
        return bytesToHex(md5InBytes).toString();
    }

    @GetMapping(path = "/current-user")
    public @ResponseBody Object getAllUsers(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Object currentUser = session.getAttribute("current-user");
        return currentUser;
    }

    @PostMapping(path = "/current-user/create")
    public @ResponseBody Object createCurrentUser(@ModelAttribute Auth body, HttpServletRequest request,
            HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        session.setAttribute("current-user", body);
        session.setMaxInactiveInterval(30 * 60);
        Object currentUser = session.getAttribute("current-user");
        System.out.println(currentUser);
        return currentUser;
    }

    @DeleteMapping(path = "/current-user/destroy", consumes = { "*/*" })
    public String deleteCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.invalidate();
        return "L'utilisateur a bien été supprimé";
    }
}