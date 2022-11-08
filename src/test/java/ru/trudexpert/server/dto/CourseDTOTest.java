package ru.trudexpert.server.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.trudexpert.server.dto.entity.CourseDTO;
import ru.trudexpert.server.entity.Course;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseDTOTest {

    private static final String TEST_COURSE_DESC = "Программа повышение квалификации";
    private static final BigDecimal TEST_COURSE_COST = BigDecimal.valueOf(2500.00);
    private static final Long TEST_COURSE_STUD_TIME = 72L;

    @Mock
    private Course entity;

    @Test
    void getFromEntity() {
        when(entity.getId()).thenReturn(1L);
        when(entity.getDescription()).thenReturn(TEST_COURSE_DESC);
        when(entity.getCost()).thenReturn(TEST_COURSE_COST);
        when(entity.getStudyingTime()).thenReturn(TEST_COURSE_STUD_TIME);

        var dto = CourseDTO.getFromEntity(entity);

        assertEquals(1L, dto.getId());
        assertEquals(TEST_COURSE_DESC, dto.getDescription());
        assertEquals(TEST_COURSE_COST.toString(), dto.getCost());
        assertEquals(TEST_COURSE_STUD_TIME, dto.getStudyingTime());

        assertNull(CourseDTO.getFromEntity(null));
    }
}