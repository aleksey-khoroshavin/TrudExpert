package ru.trudexpert.server.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/trudexpert/course")
@RequiredArgsConstructor
public class CourseController {
    @GetMapping
    public String openCoursePage(){
        return "courses";
    }
}
