package com.application.httpmodel;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @Size(min = 4, max = 30) @Pattern(regexp = "^[a-zA-Z0-9]+$") String username,
        @Size(min = 4, max = 30) @Pattern(regexp = "^[a-zA-Z0-9]+$") String name,
        @Email(message = "L'adresse mail est invalide") @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$") String email,
        @Size(min = 8) @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[-+!*$@%_])([-+!*$@%_\\w]{8,15})$", message = "Le mot de passe doit contenir au moins une lettre minuscule, une lettre majuscule, un chiffre et une longueur minimale de 8 caractères") String password) {
}
