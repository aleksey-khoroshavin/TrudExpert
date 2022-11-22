package ru.trudexpert.server.controller.document;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.trudexpert.server.controller.DocumentController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
class DocumentControllerTest {
    private static final String BASE_URL = "/trudexpert/document";

    private MockMvc mvc;

    @InjectMocks
    private DocumentController documentController;

    @BeforeEach
    void initBeforeEach() {
        mvc = MockMvcBuilders.standaloneSetup(documentController).build();
    }

    @Test
    void testMainPageTest() throws Exception {
        var response = mvc.perform(
                        get(BASE_URL).accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
