package ru.trudexpert.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CREATED, reason = "Такой слушатель уже зарегистрирован в системе")
public class ListenerAlreadyRegisteredException extends Exception{
}
