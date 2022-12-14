package ru.trudexpert.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.trudexpert.server.dto.entity.OrganizationDTO;
import ru.trudexpert.server.dto.complex.OrganizationFullDTO;
import ru.trudexpert.server.dto.shortinfo.OrganizationShortInfoDTO;
import ru.trudexpert.server.exception.NoOrganizationNameException;
import ru.trudexpert.server.exception.OrganizationAlreadyRegisteredException;
import ru.trudexpert.server.exception.OrganizationNotExistException;
import ru.trudexpert.server.service.OrganizationService;

import java.util.List;

@Controller
@RequestMapping("/trudexpert/organization")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @GetMapping
    public String openOrganizationPage() {
        return "organizations";
    }

    @GetMapping("/add")
    public String openOrganizationAddForm(Model model) {
        model.addAttribute("type", "add");
        return "organizations/organization_info";
    }

    @GetMapping("/edit")
    public String openOrganizationEditForm(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("type", "edit");

        try {
            var organizationFullDTO = organizationService.getOrganizationById(id);
            model.addAttribute("organization", organizationFullDTO);
        } catch (OrganizationNotExistException exception) {
            model.addAttribute("type", "error");
            model.addAttribute("error", exception.getMessage());
        }

        return "organizations/organization_info";
    }

    @GetMapping("/search")
    public String openSearchOrganizationPage(
            @RequestParam(required = false, defaultValue = "") String companyName,
            Model model) {

        List<OrganizationShortInfoDTO> organizations;

        if (companyName != null && !companyName.isEmpty()) {
            organizations = organizationService.getOrganizationsByName(companyName);
        } else {
            organizations = organizationService.getAllOrganizations();
        }

        if (!organizations.isEmpty()) {
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

    @PostMapping("/update")
    public ResponseEntity<String> updateOrganizationInfo(
            @RequestParam(name = "id") Long id,
            @RequestBody OrganizationDTO dto
    ) throws OrganizationNotExistException {

        organizationService.updateOrganizationInfo(dto.setId(id));
        return ResponseEntity.ok("Updated");
    }

}
