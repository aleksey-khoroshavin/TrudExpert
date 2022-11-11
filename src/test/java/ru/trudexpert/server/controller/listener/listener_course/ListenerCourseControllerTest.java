package ru.trudexpert.server.controller.listener.listener_course;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.trudexpert.server.controller.ListenerCourseController;
import ru.trudexpert.server.service.ListenerCourseService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class ListenerCourseControllerTest {
    private static final String BASE_URL = "/trudexpert/course/listener";

    private MockMvc mvc;

    @Mock
    private ListenerCourseService listenerCourseService;

    @InjectMocks
    private ListenerCourseController listenerCourseController;

    @BeforeEach
    void initBeforeEach() {
        ;
        mvc = MockMvcBuilders.standaloneSetup(listenerCourseController).build();
    }

    @Test
    void openListenerCourseSearchPage() throws Exception {
        var response = mvc.perform(
                get(BASE_URL + "/search")
                        .param("listener_id", "1")
                        .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        verify(listenerCourseService).getListenerCourses(anyLong());
        verify(listenerCourseService).getListenerFullName(anyLong());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void openListenerCourseAddPage() throws Exception {
        var response = mvc.perform(
                get(BASE_URL + "/add")
                        .param("listener_id", "1")
                        .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        verify(listenerCourseService).getCoursesNotAttachedToListener(anyLong());
        verify(listenerCourseService).getListenerFullName(anyLong());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void listenerCourseAddTest() throws Exception {
        var response = mvc.perform(
                post(BASE_URL + "/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("listener_id", "1")
                        .param("course_id", "5")).andReturn().getResponse();

        verify(listenerCourseService).addListenerToCourse(anyLong(), anyLong());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("Added", response.getContentAsString());
    }
}
