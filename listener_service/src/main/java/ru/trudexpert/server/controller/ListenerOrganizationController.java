package ru.trudexpert.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.trudexpert.server.dto.entity.ListenerOrganizationDTO;
import ru.trudexpert.server.exception.ListenerNotFoundException;
import ru.trudexpert.server.exception.OrganizationNotExistException;
import ru.trudexpert.server.service.ListenerOrganizationService;

import java.util.List;

@Controller
@RequestMapping("/trudexpert/listener/organizations")
@RequiredArgsConstructor
public class ListenerOrganizationController {
    private final ListenerOrganizationService listenerOrganizationService;

    @GetMapping()
    public String openListenerOrganizationsPage(
            @RequestParam(name = "id") Long listenerId,
            @RequestParam(name = "name") String listenerName,
            Model model){

        List<ListenerOrganizationDTO> organizations;

        organizations = listenerOrganizationService.getOrganizationsOfListener(listenerId);

        if(!organizations.isEmpty()){
            model.addAttribute("organizations", organizations);
        }

        model.addAttribute("listenerName", listenerName);
        model.addAttribute("listenerId", listenerId);

        return "/organization_listeners/organization_listeners_search";
    }

    @PostMapping("/listeners/{listenerId}/organizations/{organizationId}")
    public ResponseEntity<String> addListenerToOrganization(
            @PathVariable(value = "organizationId") Long organizationId,
            @PathVariable(value = "listenerId") Long listenerId,
            @RequestParam String post
    ) throws ListenerNotFoundException, OrganizationNotExistException {
        listenerOrganizationService.addToOrganization(organizationId, listenerId, post);
        return ResponseEntity.ok("Added");
    }

    @DeleteMapping("/listeners/{listenerId}/organizations/{organizationId}")
    public ResponseEntity<String> removeListenerFromOrganization(
            @PathVariable(value = "organizationId") Long organizationId,
            @PathVariable(value = "listenerId") Long listenerId
    ) throws ListenerNotFoundException, OrganizationNotExistException {
        listenerOrganizationService.deleteFromOrganization(organizationId, listenerId);
        return ResponseEntity.ok("Deleted");
    }
}
