package ru.trudexpert.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.trudexpert.server.domain.ListenerDetails;
import ru.trudexpert.server.exception.SnilsAlreadyRegisteredException;
import ru.trudexpert.server.service.ListenerService;

@Controller
@RequestMapping("/api/listener")
@RequiredArgsConstructor
public class ListenerController {
    private final ListenerService listenerService;

    @PostMapping("/create")
    public ResponseEntity<String> registerListener(
            @RequestBody ListenerDetails listenerDetails
    ) throws SnilsAlreadyRegisteredException {
        listenerService.createListener(listenerDetails);
        return ResponseEntity.ok("Created");
    }
}
