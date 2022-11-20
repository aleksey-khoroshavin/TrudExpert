package ru.trudexpert.server.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trudexpert/main")
@RequiredArgsConstructor
@Log4j2
public class MainController {

    @GetMapping
    public String openMainPage() {
        return "main";
    }

}
