package ru.trudexpert.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Такой СНИЛС уже зарегистрирован в системе")
public class SnilsAlreadyRegisteredException extends Exception {
    public SnilsAlreadyRegisteredException() {
        super("Такой СНИЛС уже зарегистрирован в системе");
    }
}
