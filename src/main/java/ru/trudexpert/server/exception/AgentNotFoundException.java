package ru.trudexpert.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Контрагент не найден, чтобы выполнить данную операцию!")
public class AgentNotFoundException extends Exception {

    public AgentNotFoundException() {
        super("Контрагент не найден, чтобы выполнить данную операцию!");
    }

}
