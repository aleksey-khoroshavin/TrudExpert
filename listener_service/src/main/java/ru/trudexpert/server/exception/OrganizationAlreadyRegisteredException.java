package ru.trudexpert.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Такая организация уже существует")
public class OrganizationAlreadyRegisteredException extends Exception{
    public OrganizationAlreadyRegisteredException(){
        super("Такая организация уже существует");
    }
}
