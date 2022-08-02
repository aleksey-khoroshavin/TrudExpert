package ru.trudexpert.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.trudexpert.server.dto.ListenerDTO;
import ru.trudexpert.server.entity.Listener;
import ru.trudexpert.server.exception.SnilsAlreadyRegisteredException;
import ru.trudexpert.server.service.ListenerService;

@Controller
@RequestMapping("/trudexpert/listener")
@RequiredArgsConstructor
public class ListenerController {
    private final ListenerService listenerService;

    @GetMapping
    public String openListenerPage(){
        return "listeners";
    }

    @GetMapping("/add")
    public String openListenerForm(){
        return "/listeners/listener_add";
    }

    @PostMapping(value = "/add")
    public ResponseEntity<String> registerListener(
            @RequestBody ListenerDTO listenerDTO
    ) throws SnilsAlreadyRegisteredException {
        listenerService.createListener(listenerDTO);
        return ResponseEntity.ok("Created");
    }
}
