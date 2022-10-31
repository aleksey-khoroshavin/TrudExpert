package ru.trudexpert.server.controller.organization;

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
import ru.trudexpert.server.controller.OrganizationController;
import ru.trudexpert.server.dto.entity.OrganizationDTO;
import ru.trudexpert.server.dto.shortinfo.OrganizationShortInfoDTO;
import ru.trudexpert.server.service.OrganizationService;

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
class OrganizationControllerTest {
    private static final String BASE_URL = "/trudexpert/organization";
    private MockMvc mvc;

    @Mock
    private OrganizationService organizationService;

    @InjectMocks
    private OrganizationController organizationController;

    private JacksonTester<OrganizationDTO> jsonListenerDto;

    @BeforeEach
    void initBeforeEach() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(organizationController).build();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            BASE_URL,
            BASE_URL + "/add",
            BASE_URL + "/search"
    })
    void openOrganizationPages(String url) throws Exception {
        var response = mvc.perform(
                        get(url)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        verify(organizationService, never()).getOrganizationsByName(anyString());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void openSearchByName() throws Exception {
        var organizations = List.of(
                new OrganizationShortInfoDTO().setId(1L).setName("ОАО Хим-Пром"),
                new OrganizationShortInfoDTO().setId(2L).setName("ПАО Хим-Газ"));
        when(organizationService.getOrganizationsByName(anyString())).thenReturn(organizations);
        var response = mvc.perform(
                        get(BASE_URL + "/search")
                                .param("companyName", "Хим")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        verify(organizationService).getOrganizationsByName(anyString());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void openOrganizationEditPage() throws Exception {
        var response = mvc.perform(
                get(BASE_URL + "/edit")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("id", "1")
        ).andReturn().getResponse();

        verify(organizationService).getOrganizationById(anyLong());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void registerOrganizationTest() throws Exception {
        var response = mvc.perform(
                post(BASE_URL + "/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonListenerDto.write(
                                new OrganizationDTO().setId(1L).setOrganizationName("ОАО Хим-Пром")).getJson())
        ).andReturn().getResponse();

        verify(organizationService).saveOrganization(any());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("Created", response.getContentAsString());
    }

    @Test
    void updateOrganizationTest() throws Exception {
        var response = mvc.perform(
                post(BASE_URL + "/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("id", "1")
                        .content(jsonListenerDto.write(
                                new OrganizationDTO().setId(1L).setOrganizationName("ОАО Хим-Пром")).getJson())
        ).andReturn().getResponse();

        verify(organizationService).updateOrganizationInfo(any());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("Updated", response.getContentAsString());
    }
}
