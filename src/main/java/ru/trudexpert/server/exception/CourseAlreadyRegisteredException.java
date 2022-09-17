package ru.trudexpert.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Такой курс уже зарегистрирован в системе")
public class CourseAlreadyRegisteredException extends Exception{
    public CourseAlreadyRegisteredException(){
        super("Такой курс уже зарегистрирован в системе");
    }
}
