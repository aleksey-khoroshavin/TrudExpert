package ru.trudexpert.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.trudexpert.server.dto.ListenerShortInfoDTO;
import ru.trudexpert.server.exception.ListenerNotFoundException;
import ru.trudexpert.server.exception.OrganizationNotExistException;
import ru.trudexpert.server.service.OrganizationListenerService;

import java.util.List;

@Controller
@RequestMapping("/trudexpert/organization/listeners")
@RequiredArgsConstructor
public class OrganizationListenerController {
    private final OrganizationListenerService organizationListenerService;

    @GetMapping()
    public String openOrganizationListenersPage(
            @RequestParam(required = false, defaultValue = "") String surname,
            @RequestParam Long organizationId,
            @RequestParam String organizationName,
            Model model){

        List<ListenerShortInfoDTO> listeners;

        if(surname != null && !surname.isEmpty()){
            listeners = organizationListenerService.getListenersOfOrganizationBySurname(surname, organizationId);
        }else{
            listeners = organizationListenerService.getListenersOfOrganization(organizationId);
        }

        if(!listeners.isEmpty()){
            model.addAttribute("listeners", listeners);
        }

        model.addAttribute("organizationName", organizationName);
        model.addAttribute("surname", surname);

        return "/organizations/organization_listeners/organization_listeners_search";
    }

    @PostMapping("/organizations/{organizationId}/listeners/{listenerId}")
    public ResponseEntity<String> addListenerToOrganization(
            @PathVariable(value = "organizationId") Long organizationId,
            @PathVariable(value = "listenerId") Long listenerId,
            @RequestParam String post
    ) throws ListenerNotFoundException, OrganizationNotExistException {
        organizationListenerService.addToOrganization(organizationId, listenerId, post);
        return ResponseEntity.ok("Added");
    }

    @DeleteMapping("/organizations/{organizationId}/listeners/{listenerId}")
    public ResponseEntity<String> removeListenerFromOrganization(
            @PathVariable(value = "organizationId") Long organizationId,
            @PathVariable(value = "listenerId") Long listenerId
    ) throws ListenerNotFoundException, OrganizationNotExistException {
        organizationListenerService.deleteFromOrganization(organizationId, listenerId);
        return ResponseEntity.ok("Deleted");
    }
}
