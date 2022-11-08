package ru.trudexpert.server.controller.listener;

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
import ru.trudexpert.server.controller.ListenerController;
import ru.trudexpert.server.dto.entity.ListenerDTO;
import ru.trudexpert.server.dto.shortinfo.ListenerShortInfoDTO;
import ru.trudexpert.server.service.ListenerService;

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
class ListenerControllerTest {
    private static final String BASE_URL = "/trudexpert/listener";

    private MockMvc mvc;

    @Mock
    private ListenerService listenerService;

    @InjectMocks
    private ListenerController listenerController;

    private JacksonTester<ListenerDTO> jsonListenerDto;

    @BeforeEach
    void initBeforeEach() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(listenerController).build();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            BASE_URL,
            BASE_URL + "/add",
            BASE_URL + "/search"
    })
    void openListenersPagesTest(String url) throws Exception {
        var response = mvc.perform(
                        get(url)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        verify(listenerService, never()).getListenersBySurname(anyString());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void openSearchBySurnameTest() throws Exception {
        var listeners = List.of(new ListenerShortInfoDTO().setId(1L).setSurname("Иванов"));
        when(listenerService.getListenersBySurname(anyString())).thenReturn(listeners);
        var response = mvc.perform(
                        get(BASE_URL + "/search")
                                .param("surname", "Иванов")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        verify(listenerService).getListenersBySurname(anyString());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void registerListenerTest() throws Exception {
        var response = mvc.perform(
                        post(BASE_URL + "/add")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonListenerDto.write(new ListenerDTO().setSurname("Иванов")).getJson()))
                .andReturn().getResponse();

        verify(listenerService).saveListener(any());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("Created", response.getContentAsString());
    }

    @Test
    void openListenerEditPageTest() throws Exception {
        var response = mvc.perform(
                        get(BASE_URL + "/edit")
                                .accept(MediaType.APPLICATION_JSON)
                                .param("id", "1"))
                .andReturn().getResponse();

        verify(listenerService).getListenerById(anyLong());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void updateListenerTest() throws Exception {
        var response = mvc.perform(
                        post(BASE_URL + "/update")
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("listener_id", "1")
                                .content(jsonListenerDto.write(new ListenerDTO().setSurname("Иванов")).getJson()))
                .andReturn().getResponse();

        verify(listenerService).updateListener(any());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("Updated", response.getContentAsString());
    }
}
