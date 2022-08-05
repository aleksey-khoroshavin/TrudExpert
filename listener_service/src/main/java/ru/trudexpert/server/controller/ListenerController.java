package ru.trudexpert.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.trudexpert.server.dto.ListenerDTO;
import ru.trudexpert.server.dto.ListenerShortInfoDTO;
import ru.trudexpert.server.exception.ListenerAlreadyRegisteredException;
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
    public String openListenerAddForm(Model model){
        model.addAttribute("type", "add");
        return "/listeners/listener_info";
    }

    @PostMapping("/add")
    public ResponseEntity<String> registerListener(
            @RequestBody ListenerDTO listenerDTO
    ) throws SnilsAlreadyRegisteredException, ListenerAlreadyRegisteredException {
        listenerService.createListener(listenerDTO);
        return ResponseEntity.ok("Created");
    }

    @GetMapping("/search")
    public String openSearchForm(@RequestParam(required = false, defaultValue = "") String surname, Model model){
        List<ListenerShortInfoDTO> listeners;

        if(surname != null && !surname.isEmpty()){
            listeners = listenerService.getListenersBySurname(surname);
        }else{
            listeners = listenerService.getListeners();
        }

        model.addAttribute("listeners", listeners);
        model.addAttribute("surname", surname);

        return "/listeners/listener_search";
    }

    @GetMapping("/edit")
    public String openListenerEditForm(Model model){
        model.addAttribute("type", "edit");
        return "/listeners/listener_info";
    }
}
