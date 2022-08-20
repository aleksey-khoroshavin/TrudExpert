package ru.trudexpert.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CREATED, reason = "Данная организация не найдена!")
public class OrganizationNotExistException extends Exception{
}
