package ru.trudexpert.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.trudexpert.server.dto.OrganizationDTO;
import ru.trudexpert.server.exception.NoOrganizationNameException;
import ru.trudexpert.server.exception.OrganizationAlreadyRegisteredException;
import ru.trudexpert.server.service.OrganizationService;

@Controller
@RequestMapping("/trudexpert/organization")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @GetMapping
    public String openOrganizationPage(){
        return "organizations";
    }

    @GetMapping("/add")
    public String openOrganizationRegisterForm(Model model){
        model.addAttribute("type", "add");
        return "organizations/organization_info";
    }

    @PostMapping("/add")
    public ResponseEntity<String> registerOrganization(
            @RequestBody OrganizationDTO dto) throws OrganizationAlreadyRegisteredException, NoOrganizationNameException {
        organizationService.createOrganization(dto);
        return ResponseEntity.ok("Created");
    }



}
