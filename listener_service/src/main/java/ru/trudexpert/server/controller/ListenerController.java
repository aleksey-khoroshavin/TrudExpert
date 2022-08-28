package ru.trudexpert.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.trudexpert.server.dto.ListenerDTO;
import ru.trudexpert.server.dto.ListenerShortInfoDTO;
import ru.trudexpert.server.dto.OrganizationShortInfoDTO;
import ru.trudexpert.server.exception.ListenerAlreadyRegisteredException;
import ru.trudexpert.server.exception.ListenerNotFoundException;
import ru.trudexpert.server.exception.SnilsAlreadyRegisteredException;
import ru.trudexpert.server.service.ListenerService;
import ru.trudexpert.server.service.OrganizationService;

import java.util.List;

@Controller
@RequestMapping("/trudexpert/listener")
@RequiredArgsConstructor
public class ListenerController {
    private final ListenerService listenerService;
    private final OrganizationService organizationService;

    @GetMapping
    public String openListenerPage(){
        return "listeners";
    }

    @GetMapping("/add")
    public String openListenerAddForm(Model model){
        model.addAttribute("type", "add");
        model.addAttribute("organizations", getOrganizationsForSelect());

        return "/listeners/listener_info";
    }

    @PostMapping("/add")
    public ResponseEntity<String> registerListener(
            @RequestBody ListenerDTO listenerDTO
    ) throws SnilsAlreadyRegisteredException, ListenerAlreadyRegisteredException {
        listenerService.saveListener(listenerDTO);
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

        if(!listeners.isEmpty()){
            model.addAttribute("listeners", listeners);
        }

        model.addAttribute("surname", surname);

        return "/listeners/listener_search";
    }

    @GetMapping("/edit")
    public String openListenerEditForm(@RequestParam(name = "id") Long id, Model model) throws ListenerNotFoundException {
        model.addAttribute("type", "edit");

        ListenerDTO listenerDTO = listenerService.getListenerById(id);
        model.addAttribute("listener", listenerDTO);

        model.addAttribute("organizations", getOrganizationsForSelect());

        return "/listeners/listener_info";
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateListener(
            @RequestParam(name = "listener_id") Long listenerId,
            @RequestBody ListenerDTO listenerDTO
    ) throws SnilsAlreadyRegisteredException, ListenerNotFoundException {
        listenerDTO.setId(listenerId);
        listenerService.updateListener(listenerDTO);
        return ResponseEntity.ok("Updated");
    }

    private List<OrganizationShortInfoDTO> getOrganizationsForSelect(){
        return organizationService.getAllOrganizations();
    }
}
