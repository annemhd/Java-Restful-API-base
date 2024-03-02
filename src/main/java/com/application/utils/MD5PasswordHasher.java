package com.application.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5PasswordHasher {

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte digestByte : messageDigest) {
                hexString.append(Integer.toHexString(0xFF & digestByte));
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception, e.g., log it or throw a custom exception
            throw new RuntimeException("Error hashing password with MD5", e);
        }
    }
}
