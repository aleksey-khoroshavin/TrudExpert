package ru.trudexpert.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Нет такого слушателя, чтобы выполнить данную операцию")
public class ListenerNotFoundException extends Exception{
}
