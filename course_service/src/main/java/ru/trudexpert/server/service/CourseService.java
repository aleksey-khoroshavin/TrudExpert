package ru.trudexpert.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trudexpert.server.dto.CourseDTO;
import ru.trudexpert.server.entity.Course;
import ru.trudexpert.server.exception.CourseAlreadyRegisteredException;
import ru.trudexpert.server.exception.CourseNotFoundException;
import ru.trudexpert.server.repository.CourseRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> getAllCourses(){
        return courseRepository.findAllCourses();
    }

    public List<Course> getAllCoursesByDescription(String description){
        return courseRepository.findAllByDescription('%' + description + '%');
    }

    public Course getCourseById(Long id) throws CourseNotFoundException {
        Course course = courseRepository.findById(id).orElse(null);
        if(course == null){
            throw new CourseNotFoundException();
        }else {
            return course;
        }
    }

    @Transactional
    public void saveCourse(CourseDTO courseDTO) throws CourseAlreadyRegisteredException {
        checkIsCourseAlreadyRegistered(courseDTO.getDescription());
        Course course = Course.getFromDTO(courseDTO);
        courseRepository.save(course);
    }

    private void checkIsCourseAlreadyRegistered(String desc) throws CourseAlreadyRegisteredException {
        if(courseRepository.existsByDescription(desc)){
            throw new CourseAlreadyRegisteredException();
        }
    }

}
