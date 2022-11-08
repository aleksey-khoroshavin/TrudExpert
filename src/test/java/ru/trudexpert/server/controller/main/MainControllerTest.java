package ru.trudexpert.server.controller.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.trudexpert.server.controller.MainController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
class MainControllerTest {
    private static final String BASE_URL = "/trudexpert/main/";
    private MockMvc mvc;

    @InjectMocks
    private MainController mainController;

    @BeforeEach
    void initBeforeEach() {
        mvc = MockMvcBuilders.standaloneSetup(mainController).build();
    }

    @Test
    void testMainPageTest() throws Exception {
        var response = mvc.perform(
                        get(BASE_URL).accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
