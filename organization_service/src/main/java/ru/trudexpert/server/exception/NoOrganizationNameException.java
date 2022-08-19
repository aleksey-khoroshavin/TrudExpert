package ru.trudexpert.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Не задано имя организации! Это КЛЮЧЕВОЙ АТРИБУТ!")
public class NoOrganizationNameException extends Exception {
}
