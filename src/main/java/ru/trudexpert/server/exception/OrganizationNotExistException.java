package ru.trudexpert.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Данная организация не найдена")
public class OrganizationNotExistException extends Exception {
    public OrganizationNotExistException() {
        super("Данная организация не найдена");
    }
}
