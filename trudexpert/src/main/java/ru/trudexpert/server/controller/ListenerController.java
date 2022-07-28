package ru.trudexpert.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.trudexpert.server.entity.Listener;
import ru.trudexpert.server.exception.SnilsAlreadyRegisteredException;
import ru.trudexpert.server.service.ListenerService;

@Controller
@RequestMapping("/trudexpert/listener")
@RequiredArgsConstructor
public class ListenerController {
    private final ListenerService listenerService;

//    @PostMapping("/create")
//    public ResponseEntity<String> registerListener(
//            @RequestBody Listener listener
//    ) throws SnilsAlreadyRegisteredException {
//        listenerService.createListener(listener);
//        return ResponseEntity.ok("Created");
//    }

    @GetMapping
    public String openListenerPage(){
        return "listeners";
    }
}
