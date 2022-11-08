package ru.trudexpert.server.controller.course;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.trudexpert.server.controller.CourseController;
import ru.trudexpert.server.dto.entity.CourseDTO;
import ru.trudexpert.server.service.CourseService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class CourseControllerTest {
    private static final String BASE_URL = "/trudexpert/course";

    private MockMvc mvc;

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    private JacksonTester<CourseDTO> jsonListenerDto;

    @BeforeEach
    void initBeforeEach() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(courseController).build();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            BASE_URL,
            BASE_URL + "/search",
            BASE_URL + "/add"
    })
    void openCoursePagesTest(String url) throws Exception {
        var response = mvc.perform(
                get(url).accept(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        verify(courseService, never()).getAllCoursesByDescription(anyString());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void searchCourseByDescTest() throws Exception {
        var courses = List.of(
                new CourseDTO().setId(1L).setDescription("Программа повышения квалификации"),
                new CourseDTO().setId(2L).setDescription("Программа переподготовки")
        );
        when(courseService.getAllCoursesByDescription(anyString())).thenReturn(courses);
        var response = mvc.perform(
                get(BASE_URL + "/search")
                        .param("desc", "Программа")
                        .accept(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        verify(courseService).getAllCoursesByDescription(anyString());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void openEditCoursePageTest() throws Exception {
        var response = mvc.perform(
                get(BASE_URL + "/edit")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("id", "1")
        ).andReturn().getResponse();

        verify(courseService).getCourseById(anyLong());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void registerCourseTest() throws Exception {
        var response = mvc.perform(
                post(BASE_URL + "/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonListenerDto.write(
                                new CourseDTO().setId(1L).setDescription("Программа переподготовки")).getJson())
        ).andReturn().getResponse();

        verify(courseService).saveCourse(any());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("Created", response.getContentAsString());
    }

    @Test
    void updateCourseTest() throws Exception {
        var response = mvc.perform(
                post(BASE_URL + "/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("id", "1")
                        .content(jsonListenerDto.write(
                                new CourseDTO().setId(1L).setDescription("Программа переподготовки")).getJson())
        ).andReturn().getResponse();

        verify(courseService).updateCourse(any());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("Updated", response.getContentAsString());
    }
}
