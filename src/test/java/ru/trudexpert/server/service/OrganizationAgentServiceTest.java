package ru.trudexpert.server.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.trudexpert.server.dto.entity.OrganizationAgentDTO;
import ru.trudexpert.server.entity.OrganizationAgent;
import ru.trudexpert.server.exception.AgentNotFoundException;
import ru.trudexpert.server.repository.OrganizationAgentRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrganizationAgentServiceTest {
    private static final String AGENT_NOT_FOUND_ERROR = "Контрагент не найден";

    @Mock
    private OrganizationAgentRepository organizationAgentRepository;

    @InjectMocks
    private OrganizationAgentService organizationAgentService;

    @Test
    void getAgentByIdTest() throws AgentNotFoundException {
        var entity = new OrganizationAgent()
                .setId(1L)
                .setSurname("Иванов")
                .setName("Иван")
                .setPatronymic("Иванович")
                .setPost("Директор")
                .setDocument("Приказ о назначении директором отдела");
        when(organizationAgentRepository.findById(anyLong())).thenReturn(Optional.of(entity));
        var dto = organizationAgentService.getAgentById(1L);
        assertEquals("Иванов", dto.getSurname());
        assertEquals("Иван", dto.getName());
        assertEquals("Иванович", dto.getPatronymic());
        assertEquals("Директор", dto.getPost());
    }

    @Test
    void getNotExistingAgentTest() {
        when(organizationAgentRepository.findById(anyLong())).thenReturn(Optional.empty());
        var thrown = assertThrows(AgentNotFoundException.class,
                () -> organizationAgentService.getAgentById(1L));
        assertEquals(AGENT_NOT_FOUND_ERROR, thrown.getMessage());
    }

    @Test
    void updateAgentTest() {
        var dto = new OrganizationAgentDTO()
                .setId(1L)
                .setSurname("Иванов")
                .setName("Иван")
                .setPatronymic("Иванович")
                .setPost("Директор")
                .setDocument("Приказ о назначении директором отдела");
        when(organizationAgentRepository.findById(anyLong())).thenReturn(Optional.of(OrganizationAgent.getFromDTO(dto)));
        assertDoesNotThrow(() -> organizationAgentService.updateAgent(dto));
    }

    @Test
    void updateNotExistingAgentTest() {
        var dto = new OrganizationAgentDTO()
                .setId(1L)
                .setSurname("Иванов")
                .setName("Иван")
                .setPatronymic("Иванович")
                .setPost("Директор")
                .setDocument("Приказ о назначении директором отдела");
        when(organizationAgentRepository.findById(anyLong())).thenReturn(Optional.empty());
        var thrown = assertThrows(AgentNotFoundException.class,
                () -> organizationAgentService.updateAgent(dto));
        assertEquals(AGENT_NOT_FOUND_ERROR, thrown.getMessage());
    }
}