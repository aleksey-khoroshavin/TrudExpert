package ru.trudexpert.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Слушатель не найден в системе")
public class ListenerNotFoundException extends Exception {
    public ListenerNotFoundException() {
        super("Слушатель не найден в системе");
    }
}
