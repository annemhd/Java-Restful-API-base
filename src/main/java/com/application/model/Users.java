package com.application.model;

import java.time.LocalDateTime;
import java.util.List;

import com.application.utils.CryptPassword;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @Size(min = 4, max = 30)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String username;

    @Size(min = 4, max = 30)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String name;

    @Email(message = "L'adresse mail est invalide")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String email;

    @Transient
    @Size(min = 8)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[-+!*$@%_])([-+!*$@%_\\w]{8,15})$", message = "Le mot de passe doit contenir au moins une lettre minuscule, une lettre majuscule, un chiffre et une longueur minimale de 8 caractères")
    private String password;

    private String hash_password;

    private LocalDateTime created_at;

    private List<@Size(max = 200) String> articles;

    @PrePersist
    public void prePersist() {
        this.hash_password = CryptPassword.cryptPassword(this.password);
        this.created_at = LocalDateTime.now();
    }
}
