package ru.trudexpert.server.controller.group;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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
import ru.trudexpert.server.controller.GroupController;
import ru.trudexpert.server.dto.entity.GroupDTO;
import ru.trudexpert.server.service.GroupService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
class GroupControllerTest {
    private static final String BASE_URL = "/trudexpert/group";

    private MockMvc mvc;

    @Mock
    private GroupService groupService;

    @InjectMocks
    private GroupController groupController;

    private JacksonTester<GroupDTO> jsonListenerDto;

    @BeforeEach
    void initBeforeEach() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(groupController).build();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            BASE_URL,
            BASE_URL + "/search"
    })
    void openGroupPagesTest(String url) throws Exception {
        var response = mvc.perform(get(url).accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
