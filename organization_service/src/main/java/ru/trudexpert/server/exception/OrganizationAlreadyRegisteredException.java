package ru.trudexpert.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CREATED, reason = "Такая организация уже существует")
public class OrganizationAlreadyRegisteredException extends Exception{
}