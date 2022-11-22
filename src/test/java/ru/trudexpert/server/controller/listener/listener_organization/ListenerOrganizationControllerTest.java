package ru.trudexpert.server.controller.listener.listener_organization;

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
import ru.trudexpert.server.controller.ListenerOrganizationController;
import ru.trudexpert.server.service.ListenerOrganizationService;
import ru.trudexpert.server.service.ListenerService;
import ru.trudexpert.server.service.OrganizationService;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class ListenerOrganizationControllerTest {
    private static final String BASE_URL = "/trudexpert/listener/organizations";

    private MockMvc mvc;

    @Mock
    private ListenerOrganizationService listenerOrganizationService;

    @Mock
    private OrganizationService organizationService;

    @Mock
    private ListenerService listenerService;

    @InjectMocks
    private ListenerOrganizationController listenerOrganizationController;

    @BeforeEach
    void initBeforeEach() {
        ;
        mvc = MockMvcBuilders.standaloneSetup(listenerOrganizationController).build();
    }

    @Test
    void openMainPageTest() throws Exception {
        var response = mvc.perform(
                get(BASE_URL)
                        .param("id", "1")
                        .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        verify(listenerOrganizationService).getOrganizationsOfListener(anyLong());
        verify(listenerService).getListenerName(anyLong());

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void openOrganizationAddPageTest() throws Exception {
        var response = mvc.perform(
                get(BASE_URL + "/add")
                        .param("id", "1")
                        .param("name", "Иванов")
                        .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        verify(organizationService).getAllOrganizationsNotAttachedToListener(anyLong());

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void addListenerToOrganizationTest() throws Exception {
        var response = mvc.perform(
                post(BASE_URL + "/add")
                        .param("organizationId", "34")
                        .param("listenerId", "3")
                        .param("post", "Директор")
                        .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        verify(listenerOrganizationService).addToOrganization(anyLong(), anyLong(), anyString());

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("Added", response.getContentAsString());
    }

    @Test
    void removeListenerFromOrganizationTest() throws Exception {
        assertDoesNotThrow(() -> mvc.perform(
                get(BASE_URL + "/delete")
                        .param("organizationId", "34")
                        .param("listenerId", "5")
                        .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse());

        verify(listenerOrganizationService).deleteFromOrganization(anyLong(), anyLong());
    }
}
