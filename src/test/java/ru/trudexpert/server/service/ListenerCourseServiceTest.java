package ru.trudexpert.server.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.trudexpert.server.entity.Course;
import ru.trudexpert.server.exception.CourseNotFoundException;
import ru.trudexpert.server.exception.ListenerNotFoundException;
import ru.trudexpert.server.repository.CourseRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListenerCourseServiceTest {
    private static final String TEST_FULL_NAME = "Иванов Иван Иванович";
    private static final String COURSE_NOT_FOUND_ERROR = "Курс не найден в системе";
    private static final String LISTENER_NOT_FOUND_ERROR = "Слушатель не найден в системе";

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private ListenerCourseService listenerCourseService;

    @Test
    void getListenerCoursesTest() {
        var courseDto1 = new Course()
                .setId(1L)
                .setDescription("Программа повышения квалификации")
                .setCost(BigDecimal.valueOf(2500.00))
                .setStudyingTime(64L);

        var courseDto2 = new Course()
                .setId(2L)
                .setDescription("Программа переподготовки")
                .setCost(BigDecimal.valueOf(1500.00))
                .setStudyingTime(32L);

        var courseDto3 = new Course()
                .setId(3L)
                .setDescription("Инструктаж по технике безопасности")
                .setCost(BigDecimal.valueOf(500.00))
                .setStudyingTime(4L);

        var courseDTOs = List.of(courseDto1, courseDto2, courseDto3);
        when(courseRepository.findListenerCourses(anyLong())).thenReturn(courseDTOs);

        var result = listenerCourseService.getListenerCourses(1L);
        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    void getListenerFullNameTest() {
        when(courseRepository.getListenerName(anyLong())).thenReturn(TEST_FULL_NAME);
        var result = listenerCourseService.getListenerFullName(1L);
        assertEquals(TEST_FULL_NAME, result);
    }

    @Test
    void getCoursesNotAttachedToListenerTest() {
        var courseDto1 = new Course()
                .setId(1L)
                .setDescription("Программа повышения квалификации")
                .setCost(BigDecimal.valueOf(2500.00))
                .setStudyingTime(64L);

        var courseDto2 = new Course()
                .setId(2L)
                .setDescription("Программа переподготовки")
                .setCost(BigDecimal.valueOf(1500.00))
                .setStudyingTime(32L);

        var courseDto3 = new Course()
                .setId(3L)
                .setDescription("Инструктаж по технике безопасности")
                .setCost(BigDecimal.valueOf(500.00))
                .setStudyingTime(4L);

        var courseDTOs = List.of(courseDto1, courseDto2, courseDto3);
        when(courseRepository.findCoursesNotAttachedToListener(anyLong())).thenReturn(courseDTOs);

        var result = listenerCourseService.getCoursesNotAttachedToListener(1L);
        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    void addListenerToCourseTest() {
        when(courseRepository.checkIsCourseExists(anyLong())).thenReturn(true);
        when(courseRepository.checkIsListenerExists(anyLong())).thenReturn(true);

        doNothing().when(courseRepository).addListenerToCourse(anyLong(), anyLong());

        assertDoesNotThrow(() -> listenerCourseService.addListenerToCourse(1L, 1L));
    }

    @Test
    void addNotExistingListenerToCourseTest() {
        when(courseRepository.checkIsCourseExists(anyLong())).thenReturn(true);
        when(courseRepository.checkIsListenerExists(anyLong())).thenReturn(false);

        var thrown = assertThrows(ListenerNotFoundException.class,
                () -> listenerCourseService.addListenerToCourse(1L, 1L));
        assertEquals(LISTENER_NOT_FOUND_ERROR, thrown.getMessage());
    }

    @Test
    void addListenerToNotExistingCourseTest() {
        when(courseRepository.checkIsCourseExists(anyLong())).thenReturn(false);

        var thrown = assertThrows(CourseNotFoundException.class,
                () -> listenerCourseService.addListenerToCourse(1L, 1L));
        assertEquals(COURSE_NOT_FOUND_ERROR, thrown.getMessage());
    }
}