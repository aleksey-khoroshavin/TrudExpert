package ru.trudexpert.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trudexpert.server.dto.entity.CourseDTO;
import ru.trudexpert.server.exception.CourseNotFoundException;
import ru.trudexpert.server.exception.ListenerNotFoundException;
import ru.trudexpert.server.repository.CourseRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListenerCourseService {

    private final CourseRepository courseRepository;

    @Transactional
    public List<CourseDTO> getListenerCourses(Long listenerId) {
        return courseRepository.findListenerCourses(listenerId).stream().map(CourseDTO::getFromEntity).toList();
    }

    @Transactional
    public String getListenerFullName(Long listenerId) {
        return courseRepository.getListenerName(listenerId);
    }

    @Transactional
    public List<CourseDTO> getCoursesNotAttachedToListener(Long listenerId) {
        return courseRepository.findCoursesNotAttachedToListener(listenerId).stream().map(CourseDTO::getFromEntity).toList();
    }

    @Transactional
    public void addListenerToCourse(Long listenerId, Long courseId) throws CourseNotFoundException, ListenerNotFoundException {

        if (!courseRepository.checkIsCourseExists(courseId)) {
            throw new CourseNotFoundException();
        }

        if (!courseRepository.checkIsListenerExists(listenerId)) {
            throw new ListenerNotFoundException();
        }

        courseRepository.addListenerToCourse(listenerId, courseId);
    }
}
