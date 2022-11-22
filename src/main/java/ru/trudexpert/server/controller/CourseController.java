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
import ru.trudexpert.server.dto.entity.CourseDTO;
import ru.trudexpert.server.exception.CourseAlreadyRegisteredException;
import ru.trudexpert.server.exception.CourseNotFoundException;
import ru.trudexpert.server.service.CourseService;

import java.util.List;


@Controller
@RequestMapping("/trudexpert/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public String openCoursePage() {
        return "courses";
    }

    @GetMapping("/search")
    public String openCourseSearchPage(@RequestParam(required = false, defaultValue = "") String desc, Model model) {
        List<CourseDTO> courses;

        if (desc != null && !desc.isEmpty()) {
            courses = courseService.getAllCoursesByDescription(desc);
        } else {
            courses = courseService.getAllCourses();
        }

        if (!courses.isEmpty()) {
            model.addAttribute("courses", courses);
        }

        model.addAttribute("desc", desc);

        return "/courses/course_search";
    }

    @GetMapping("/add")
    public String openAddCoursePage(Model model) {
        model.addAttribute("type", "add");

        return "/courses/course_info";
    }

    @GetMapping("/edit")
    public String openEditCoursePage(@RequestParam(name = "id") Long id, Model model) throws CourseNotFoundException {
        model.addAttribute("type", "edit");

        var course = courseService.getCourseById(id);
        model.addAttribute("course", CourseDTO.getFromEntity(course));

        return "/courses/course_info";
    }

    @PostMapping("/add")
    public ResponseEntity<String> registerCourse(
            @RequestBody CourseDTO courseDTO
    ) throws CourseAlreadyRegisteredException {
        courseService.saveCourse(courseDTO);
        return ResponseEntity.ok("Created");
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateCourse(
            @RequestParam(name = "id") Long id,
            @RequestBody CourseDTO courseDTO
    ) throws CourseAlreadyRegisteredException, CourseNotFoundException {
        courseService.updateCourse(courseDTO.setId(id));
        return ResponseEntity.ok("Updated");
    }
}
