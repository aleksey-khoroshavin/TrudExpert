package ru.trudexpert.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.trudexpert.server.dto.shortinfo.OrganizationShortInfoDTO;
import ru.trudexpert.server.exception.ListenerNotFoundException;
import ru.trudexpert.server.exception.OrganizationNotExistException;
import ru.trudexpert.server.service.ListenerOrganizationService;
import ru.trudexpert.server.service.ListenerService;
import ru.trudexpert.server.service.OrganizationService;

import java.util.List;

@Controller
@RequestMapping("/trudexpert/listener/organizations")
@RequiredArgsConstructor
public class ListenerOrganizationController {
    private final ListenerOrganizationService listenerOrganizationService;
    private final OrganizationService organizationService;

    private final ListenerService listenerService;

    private static final String ORGANIZATIONS_ATTR_STR = "organizations";
    private static final String LISTENER_NAME_ATTR_STR = "listenerName";
    private static final String LISTENER_ID_ATTR_STR = "listenerId";

    @GetMapping()
    public String openListenerOrganizationsPage(
            @RequestParam(name = "id") Long listenerId,
            Model model) throws ListenerNotFoundException {

        var organizations = listenerOrganizationService.getOrganizationsOfListener(listenerId);

        if (!organizations.isEmpty()) {
            model.addAttribute(ORGANIZATIONS_ATTR_STR, organizations);
        }

        model.addAttribute(LISTENER_NAME_ATTR_STR, listenerService.getListenerName(listenerId));
        model.addAttribute(LISTENER_ID_ATTR_STR, listenerId);

        return "/listener_organizations/listener_organizations_search";
    }

    @GetMapping("/add")
    public String openOrganizationAddPage(
            @RequestParam(name = "id") Long listenerId,
            @RequestParam(name = "name") String listenerName,
            Model model) {

        List<OrganizationShortInfoDTO> organizations = organizationService
                .getAllOrganizationsNotAttachedToListener(listenerId);

        model.addAttribute(ORGANIZATIONS_ATTR_STR, organizations);
        model.addAttribute(LISTENER_ID_ATTR_STR, listenerId);
        model.addAttribute(LISTENER_NAME_ATTR_STR, listenerName);

        return "/listener_organizations/listener_organizations_add";
    }

    @PostMapping("/add")
    public ResponseEntity<String> addListenerToOrganization(
            @RequestParam(value = "organizationId") Long organizationId,
            @RequestParam(value = "listenerId") Long listenerId,
            @RequestParam String post
    ) throws ListenerNotFoundException, OrganizationNotExistException {
        listenerOrganizationService.addToOrganization(organizationId, listenerId, post);
        return ResponseEntity.ok("Added");
    }

    @GetMapping("/delete")
    public String removeListenerFromOrganization(
            @RequestParam(value = "organizationId") Long organizationId,
            @RequestParam(value = "listenerId") Long listenerId
    ) throws ListenerNotFoundException, OrganizationNotExistException {
        listenerOrganizationService.deleteFromOrganization(organizationId, listenerId);
        return String.format("redirect:/trudexpert/listener/organizations?id=%s", listenerId);
    }
}
