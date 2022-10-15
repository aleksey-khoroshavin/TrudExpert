package ru.trudexpert.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trudexpert.server.dto.CourseDTO;
import ru.trudexpert.server.entity.Course;
import ru.trudexpert.server.exception.CourseAlreadyRegisteredException;
import ru.trudexpert.server.exception.CourseNotFoundException;
import ru.trudexpert.server.repository.CourseRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAllCourses()
                .stream()
                .map(CourseDTO::getFromEntity)
                .toList();
    }

    public List<CourseDTO> getAllCoursesByDescription(String description) {
        return courseRepository.findAllByDescription('%' + description + '%')
                .stream()
                .map(CourseDTO::getFromEntity)
                .toList();
    }

    public Course getCourseById(Long id) throws CourseNotFoundException {
        var course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            throw new CourseNotFoundException();
        } else {
            return course;
        }
    }

    @Transactional
    public void saveCourse(CourseDTO courseDTO) throws CourseAlreadyRegisteredException {
        checkIsCourseAlreadyRegistered(courseDTO.getDescription());
        var course = Course.getFromDTO(courseDTO);
        courseRepository.save(course);
    }

    @Transactional
    public void updateCourse(CourseDTO courseDTO) throws CourseAlreadyRegisteredException, CourseNotFoundException {

        var course = courseRepository.findById(courseDTO.getId()).orElse(null);
        if (course == null) {
            throw new CourseNotFoundException();
        }

        if (!courseDTO.getDescription().equals(course.getDescription())) {
            checkIsCourseAlreadyRegistered(courseDTO.getDescription());
        }

        course.setId(courseDTO.getId())
                .setCost(BigDecimal.valueOf(Double.parseDouble(courseDTO.getCost())))
                .setStudyingTime(course.getStudyingTime())
                .setDescription(courseDTO.getDescription());

        courseRepository.save(course);
    }

    private void checkIsCourseAlreadyRegistered(String desc) throws CourseAlreadyRegisteredException {
        if (courseRepository.existsByDescription(desc)) {
            throw new CourseAlreadyRegisteredException();
        }
    }

}
