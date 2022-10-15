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
import ru.trudexpert.server.dto.entity.OrganizationAgentDTO;
import ru.trudexpert.server.exception.AgentNotFoundException;
import ru.trudexpert.server.service.OrganizationAgentService;

@Controller
@RequestMapping("/trudexpert/organization/agent")
@RequiredArgsConstructor
public class OrganizationAgentController {

    private final OrganizationAgentService organizationAgentService;

    @GetMapping("/edit")
    public String openOrganizationAgentInfoPage(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "companyName") String companyName,
            Model model) {

        OrganizationAgentDTO agentDTO = organizationAgentService.getAgentById(id);

        model.addAttribute("agent", agentDTO);
        model.addAttribute("company", companyName);

        return "organizations/organization_agent/organization_agent_info";
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateAgent(
            @RequestParam(name = "agent_id") Long agentId,
            @RequestBody OrganizationAgentDTO agentDTO
    ) throws AgentNotFoundException {
        agentDTO.setId(agentId);
        organizationAgentService.updateAgent(agentDTO);
        return ResponseEntity.ok("Updated");
    }

}
