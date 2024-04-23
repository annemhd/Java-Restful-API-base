package com.application.httpmodel;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateUserPasswordRequest(
    @Size(min = 8)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[-+!*$@%_])([-+!*$@%_\\w]{8,15})$", message = "Le mot de passe doit contenir au moins une lettre minuscule, une lettre majuscule, un chiffre et une longueur minimale de 8 caractères")
    String password) {
}
