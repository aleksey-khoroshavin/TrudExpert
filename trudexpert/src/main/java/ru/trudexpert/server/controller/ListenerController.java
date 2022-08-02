package ru.trudexpert.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.trudexpert.server.dto.ListenerDTO;
import ru.trudexpert.server.dto.ListenerShortInfoDTO;
import ru.trudexpert.server.entity.Listener;
import ru.trudexpert.server.exception.SnilsAlreadyRegisteredException;
import ru.trudexpert.server.service.ListenerService;

import java.util.List;

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

    @PostMapping("/add")
    public ResponseEntity<String> registerListener(
            @RequestBody ListenerDTO listenerDTO
    ) throws SnilsAlreadyRegisteredException {
        listenerService.createListener(listenerDTO);
        return ResponseEntity.ok("Created");
    }

    @GetMapping("/search")
    public String openSearchForm(@RequestParam(required = false, defaultValue = "") String surname, Model model){
//        List<ListenerShortInfoDTO> listeners;
//
//        if(surname != null && !surname.isEmpty()){
//            listeners = listenerService.getListenersBySurname(surname);
//        }else{
//            listeners = listenerService.getListeners();
//        }

        return "/listeners/listener_search";
    }
}
