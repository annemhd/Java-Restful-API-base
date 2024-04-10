package com.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "L'utilisateur est introuvable")
public class UserNotFoundException extends RuntimeException {
}
