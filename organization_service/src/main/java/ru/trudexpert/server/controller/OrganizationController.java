package ru.trudexpert.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

//    @GetMapping("/add/agent")
//    public String openOrganizationAgentForm(){
//        //TODO:create html page for this form
//    }
//
//    @GetMapping("/add/listeners")
//    public String openOrganizationListenersPage(){
//        //TODO:create html page for this table
//    }





}
