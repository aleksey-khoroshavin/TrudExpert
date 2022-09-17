package ru.trudexpert.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Слушатель не найден!")
public class ListenerNotFoundException extends Exception{
    public ListenerNotFoundException(){
        super("Слушатель не найден!");
    }
}
