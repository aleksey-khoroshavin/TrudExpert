package ru.trudexpert.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.trudexpert.server.service.OrganizationAgentService;

@Controller
@RequestMapping("/trudexpert/organization/agent")
@RequiredArgsConstructor
public class OrganizationAgentController {
    private final OrganizationAgentService organizationAgentService;


}
