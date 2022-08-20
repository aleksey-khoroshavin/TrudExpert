package ru.trudexpert.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.trudexpert.server.dto.OrganizationAgentDTO;
import ru.trudexpert.server.dto.OrganizationFullDTO;
import ru.trudexpert.server.dto.OrganizationShortInfoDTO;
import ru.trudexpert.server.exception.NoOrganizationNameException;
import ru.trudexpert.server.exception.OrganizationAlreadyRegisteredException;
import ru.trudexpert.server.service.OrganizationService;

import java.util.List;

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
    public String openOrganizationAddForm(Model model){
        model.addAttribute("type", "add");
        return "organizations/organization_info";
    }

    @GetMapping("/agent/edit")
    public String openOrganizationAgentInfoPage(@RequestParam(name = "id") Long id, Model model) {

        OrganizationAgentDTO agentDTO = organizationService.getAgentById(id);
        model.addAttribute("agent", agentDTO);

        return "organizations/organization_agent/organization_agent_info";
    }


    @GetMapping("/search")
    public String openSearchOrganizationPage(
            @RequestParam(required = false, defaultValue = "") String companyName,
            Model model){

        List<OrganizationShortInfoDTO> organizations;

        if(companyName != null && !companyName.isEmpty()){
            organizations = organizationService.getOrganizationsByName(companyName);
        }else{
            organizations = organizationService.getAllOrganizations();
        }

        if(!organizations.isEmpty()){
            model.addAttribute("organizations", organizations);
        }

        model.addAttribute("companyName", companyName);

        return "/organizations/organization_search";
    }

    @PostMapping("/add")
    public ResponseEntity<String> registerOrganization(
            @RequestBody OrganizationFullDTO organizationFullDTO) throws OrganizationAlreadyRegisteredException, NoOrganizationNameException {
        organizationService.saveOrganization(organizationFullDTO);
        return ResponseEntity.ok("Created");
    }

}
