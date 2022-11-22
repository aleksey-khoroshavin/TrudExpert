package ru.trudexpert.server.controller.organization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.trudexpert.server.controller.OrganizationAgentController;
import ru.trudexpert.server.dto.entity.OrganizationAgentDTO;
import ru.trudexpert.server.service.OrganizationAgentService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class OrganizationAgentControllerTest {
    private static final String BASE_URL = "/trudexpert/organization/agent";

    private MockMvc mvc;

    @Mock
    private OrganizationAgentService agentService;

    @InjectMocks
    private OrganizationAgentController agentController;

    private JacksonTester<OrganizationAgentDTO> jacksonTester;

    @BeforeEach
    void initBeforeEach() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(agentController).build();
    }

    @Test
    void openOrgAgentEditPageTest() throws Exception {
        var response = mvc.perform(
                        get(BASE_URL + "/edit")
                                .accept(MediaType.APPLICATION_JSON)
                                .param("id", "1")
                                .param("companyName", "ОАО Хим-Пром"))
                .andReturn().getResponse();

        verify(agentService).getAgentById(anyLong());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void updateOrgAgentTest() throws Exception {
        var response = mvc.perform(
                post(BASE_URL + "/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("agent_id", "1")
                        .content(jacksonTester.write(
                                new OrganizationAgentDTO().setId(1L).setSurname("Иванов")).getJson())
        ).andReturn().getResponse();

        verify(agentService).updateAgent(any());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("Updated", response.getContentAsString());
    }

}
