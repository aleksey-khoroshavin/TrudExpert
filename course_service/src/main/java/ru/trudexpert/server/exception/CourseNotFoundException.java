package ru.trudexpert.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Курс не найден в системе!")
public class CourseNotFoundException extends Exception{
    public CourseNotFoundException(){
        super("Курс не найден в системе");
    }
}
