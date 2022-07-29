package ru.trudexpert.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trudexpert/group")
@RequiredArgsConstructor
public class GroupController {
    @GetMapping
    public String openGroupPAge(){
        return "groups";
    }
}
