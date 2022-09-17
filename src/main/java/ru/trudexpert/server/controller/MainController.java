package ru.trudexpert.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trudexpert/main")
@RequiredArgsConstructor
public class MainController {

    @GetMapping
    public String openMainPage(){
        return "main";
    }

}
