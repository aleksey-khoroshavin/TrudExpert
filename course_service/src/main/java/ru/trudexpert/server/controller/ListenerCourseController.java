package ru.trudexpert.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.trudexpert.server.dto.CourseDTO;
import ru.trudexpert.server.exception.CourseNotFoundException;
import ru.trudexpert.server.exception.ListenerNotFoundException;
import ru.trudexpert.server.service.ListenerCourseService;

import java.util.List;

@Controller
@RequestMapping("/trudexpert/course/listener")
@RequiredArgsConstructor
public class ListenerCourseController {

    private final ListenerCourseService listenerCourseService;

    @GetMapping("/search")
    public String openListenerCoursesPage(
            @RequestParam(name = "listener_id") Long listenerId,
            Model model
    ){
        List<CourseDTO> courses;

        courses = listenerCourseService.getListenerCourses(listenerId);

        if(!courses.isEmpty()){
            model.addAttribute("courses", courses);
        }

        model.addAttribute("listenerName", listenerCourseService.getListenerFullName(listenerId));
        model.addAttribute("listenerId", listenerId);

        return "/listener_courses/listener_courses_search";
    }

    @GetMapping("/add")
    public String openListenerCourseAddPage(
            @RequestParam(name = "listener_id") Long listenerId,
            Model model
    ){
        List<CourseDTO> courses = listenerCourseService.getCoursesNotAttachedToListener(listenerId);

        model.addAttribute("courses", courses);
        model.addAttribute("listenerName", listenerCourseService.getListenerFullName(listenerId));
        model.addAttribute("listenerId", listenerId);


        return "/listener_courses/listener_courses_add";
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCourseToListener(
            @RequestParam(name = "listener_id") Long listenerId,
            @RequestParam(name = "course_id") Long courseId
    ) throws CourseNotFoundException, ListenerNotFoundException {
        listenerCourseService.addListenerToCourse(listenerId, courseId);
        return ResponseEntity.ok("Added");
    }
}
