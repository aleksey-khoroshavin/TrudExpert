package ru.trudexpert.server.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.trudexpert.server.dto.entity.CourseDTO;
import ru.trudexpert.server.entity.Course;
import ru.trudexpert.server.exception.CourseAlreadyRegisteredException;
import ru.trudexpert.server.exception.CourseNotFoundException;
import ru.trudexpert.server.repository.CourseRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {
    private static final String COURSE_NOT_FOUND_ERROR = "Курс не найден в системе";
    private static final String COURSE_ALREADY_EXIST_ERROR = "Такой курс уже зарегистрирован в системе";

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    @Test
    void getAllCoursesTest() {
        var course1 = new Course()
                .setId(1L)
                .setDescription("Программа повышения квалификации")
                .setCost(BigDecimal.valueOf(2500.00))
                .setStudyingTime(64L);

        var course2 = new Course()
                .setId(2L)
                .setDescription("Программа переподготовки специалиста")
                .setCost(BigDecimal.valueOf(1500.00))
                .setStudyingTime(48L);

        var course3 = new Course()
                .setId(3L)
                .setDescription("Инструктаж по технике безопасности")
                .setCost(BigDecimal.valueOf(500.00))
                .setStudyingTime(3L);

        var courses = List.of(course1, course2, course3);

        when(courseRepository.findAllCourses()).thenReturn(courses);

        var result = courseService.getAllCourses();

        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    void getAllCoursesByDescription() {
        var course1 = new Course()
                .setId(1L)
                .setDescription("Программа повышения квалификации")
                .setCost(BigDecimal.valueOf(2500.00))
                .setStudyingTime(64L);

        var course2 = new Course()
                .setId(2L)
                .setDescription("Программа переподготовки специалиста")
                .setCost(BigDecimal.valueOf(1500.00))
                .setStudyingTime(48L);

        var courses = List.of(course1, course2);

        when(courseRepository.findAllByDescription(anyString())).thenReturn(courses);

        var result = courseService.getAllCoursesByDescription("Программа");

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void getCourseByIdTest() throws CourseNotFoundException {
        var course = new Course()
                .setId(1L)
                .setDescription("Программа повышения квалификации")
                .setCost(BigDecimal.valueOf(2500.00))
                .setStudyingTime(64L);

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        var result = courseService.getCourseById(1L);
        assertEquals(1L, result.getId());
        assertEquals(BigDecimal.valueOf(2500.00), result.getCost());
        assertEquals(64L, result.getStudyingTime());
        assertEquals("Программа повышения квалификации", result.getDescription());
    }

    @Test
    void courseNotFoundExceptionTest() {
        when(courseRepository.findById(anyLong())).thenReturn(Optional.empty());

        var thrown = assertThrows(CourseNotFoundException.class,
                () -> courseService.getCourseById(1L));
        assertEquals(COURSE_NOT_FOUND_ERROR, thrown.getMessage());
    }

    @Test
    void saveCourseTest() {
        var courseDto = new CourseDTO()
                .setId(1L)
                .setDescription("Программа повышения квалификации")
                .setCost("2500.00")
                .setStudyingTime(64L);

        when(courseRepository.existsByDescription(anyString())).thenReturn(false);
        when(courseRepository.save(any())).thenReturn(Course.getFromDTO(courseDto));
        assertDoesNotThrow(() -> courseService.saveCourse(courseDto));
    }

    @Test
    void updateCourseTest() {
        var oldCourse = new Course()
                .setId(1L)
                .setDescription("Программа повышения квалификации высших специалистов")
                .setCost(BigDecimal.valueOf(3500.00))
                .setStudyingTime(72L);

        var newCourseDto = new CourseDTO()
                .setId(1L)
                .setDescription("Программа повышения квалификации")
                .setCost("2500.00")
                .setStudyingTime(64L);

        when(courseRepository.findById(1L)).thenReturn(Optional.of(oldCourse));
        when(courseRepository.existsByDescription(anyString())).thenReturn(false);
        when(courseRepository.save(any())).thenReturn(Course.getFromDTO(newCourseDto));
        assertDoesNotThrow(() -> courseService.updateCourse(newCourseDto));
    }

    @Test
    void updateNotExistingCourseTest() {
        var newCourseDto = new CourseDTO()
                .setId(1L)
                .setDescription("Программа повышения квалификации")
                .setCost("2500.00")
                .setStudyingTime(64L);

        when(courseRepository.findById(anyLong())).thenReturn(Optional.empty());
        var thrown = assertThrows(CourseNotFoundException.class,
                () -> courseService.updateCourse(newCourseDto));
        assertEquals(COURSE_NOT_FOUND_ERROR, thrown.getMessage());
    }

    @Test
    void updateCourseWithExistingDescTest() {
        var oldCourse = new Course()
                .setId(1L)
                .setDescription("Программа повышения квалификации высших специалистов")
                .setCost(BigDecimal.valueOf(3500.00))
                .setStudyingTime(72L);

        var newCourseDto = new CourseDTO()
                .setId(1L)
                .setDescription("Программа повышения квалификации")
                .setCost("2500.00")
                .setStudyingTime(64L);

        when(courseRepository.findById(1L)).thenReturn(Optional.of(oldCourse));
        when(courseRepository.existsByDescription(anyString())).thenReturn(true);

        var thrown = assertThrows(CourseAlreadyRegisteredException.class,
                () -> courseService.updateCourse(newCourseDto));
        assertEquals(COURSE_ALREADY_EXIST_ERROR, thrown.getMessage());
    }
}