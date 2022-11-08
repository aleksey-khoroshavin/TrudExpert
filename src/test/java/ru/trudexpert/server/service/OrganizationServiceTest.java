package ru.trudexpert.server.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.trudexpert.server.dto.complex.OrganizationFullDTO;
import ru.trudexpert.server.dto.entity.OrganizationAgentDTO;
import ru.trudexpert.server.dto.entity.OrganizationDTO;
import ru.trudexpert.server.entity.Organization;
import ru.trudexpert.server.entity.OrganizationAgent;
import ru.trudexpert.server.exception.NoOrganizationNameException;
import ru.trudexpert.server.exception.OrganizationAlreadyRegisteredException;
import ru.trudexpert.server.exception.OrganizationNotExistException;
import ru.trudexpert.server.repository.OrganizationAgentRepository;
import ru.trudexpert.server.repository.OrganizationRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrganizationServiceTest {
    private static final String ORGANIZATION_NOT_EXISTING_ERROR = "Данная организация не найдена";
    private static final String ORGANIZATION_NAME_ERROR = "Не задано имя организации. Это КЛЮЧЕВОЙ АТРИБУТ";
    private static final String ORGANIZATION_ALREADY_REGISTERED_ERROR = "Такая организация уже существует";

    @Mock
    private OrganizationRepository organizationRepository;

    @Mock
    private OrganizationAgentRepository agentRepository;

    @InjectMocks
    private OrganizationService organizationService;

    @Test
    void saveOrganizationTest() {
        var orgDto = new OrganizationDTO()
                .setId(1L)
                .setOrganizationName("ООО Хим-Пром")
                .setEmail("him-prom@gmail.com");
        var agentDto = new OrganizationAgentDTO().setDocument("Приказ о назначении директором");
        var fullDto = new OrganizationFullDTO()
                .setOrganizationDTO(orgDto)
                .setOrganizationAgentDTO(agentDto);

        when(organizationRepository.save(notNull())).thenReturn(Organization.getFromDTO(orgDto));
        assertDoesNotThrow(() -> organizationService.saveOrganization(fullDto));
    }

    @Test
    void saveOrganizationWithNoName() {
        var dto = new OrganizationDTO().setId(1L).setOrganizationName("");
        var fullDto = new OrganizationFullDTO().setOrganizationDTO(dto);

        var thrown = assertThrows(NoOrganizationNameException.class,
                () -> organizationService.saveOrganization(fullDto));
        assertEquals(ORGANIZATION_NAME_ERROR, thrown.getMessage());
    }

    @Test
    void saveOrganizationWithExistingName() {
        var dto = new OrganizationDTO().setId(1L).setOrganizationName("ООО Хим-Пром");
        var fullDto = new OrganizationFullDTO().setOrganizationDTO(dto);

        when(organizationRepository.existsByName(anyString())).thenReturn(true);
        var thrown = assertThrows(OrganizationAlreadyRegisteredException.class,
                () -> organizationService.saveOrganization(fullDto));
        assertEquals(ORGANIZATION_ALREADY_REGISTERED_ERROR, thrown.getMessage());
    }

    @Test
    void getOrganizationsTest() {
        var organization1 = new Organization()
                .setId(1L)
                .setName("ПАО Хим-Пром");
        var organization2 = new Organization()
                .setId(2L)
                .setName("ООО Бетон-НСК");

        when(organizationRepository.findAllOrganizations()).thenReturn(List.of(organization1, organization2));
        when(organizationRepository.findAllByName(anyString())).thenReturn(List.of(organization1));

        var resultAll = organizationService.getAllOrganizations();
        assertNotNull(resultAll);
        assertEquals(2, resultAll.size());

        var resultByName = organizationService.getOrganizationsByName("ПАО Хим-Пром");
        assertNotNull(resultByName);
        assertEquals(1, resultByName.size());
    }

    @Test
    void getAllOrganizationsNotAttachedToListenerTest() {
        var organization1 = new Organization().setId(1L).setName("ООО Хим-Пром");
        var organization2 = new Organization().setId(2L).setName("ОАО Бетон-НСК");
        var listenerOrgList = List.of(organization1, organization2);

        when(organizationRepository.findAllOrganizationsNotAttachedToListener(anyLong())).thenReturn(listenerOrgList);
        var result = organizationService.getAllOrganizationsNotAttachedToListener(1L);
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void getOrganizationByIdTest() throws OrganizationNotExistException {
        var organization = new Organization()
                .setId(1L)
                .setName("ООО Хим-Пром");
        var agent = new OrganizationAgent()
                .setId(1L)
                .setDocument("Приказ о назначении управляющим цеха");

        when(organizationRepository.findById(anyLong())).thenReturn(Optional.of(organization));
        when(agentRepository.findById(anyLong())).thenReturn(Optional.of(agent));

        var dto = organizationService.getOrganizationById(1L);
        assertNotNull(dto);
        assertEquals(1L, dto.getOrganizationDTO().getId());
        assertEquals(1L, dto.getOrganizationAgentDTO().getId());
    }

    @Test
    void getNotExistingOrganizationById() {
        when(organizationRepository.findById(anyLong())).thenReturn(Optional.empty());
        var thrown = assertThrows(OrganizationNotExistException.class,
                () -> organizationService.getOrganizationById(1L));
        assertEquals(ORGANIZATION_NOT_EXISTING_ERROR, thrown.getMessage());
    }

    @Test
    void updateOrganizationInfoTest() {
        var oldEntity = new Organization()
                .setId(1L)
                .setName("ОАО Хим-Пром");
        var dto = new OrganizationDTO()
                .setId(1L)
                .setOrganizationName("ПАО Хим-Пром-НСК");
        when(organizationRepository.findById(1L)).thenReturn(Optional.of(oldEntity));
        when(organizationRepository.save(notNull())).thenReturn(Organization.getFromDTO(dto));
        assertDoesNotThrow(() -> organizationService.updateOrganizationInfo(dto));
    }

    @Test
    void updateNotExistingOrganizationTest() {
        var dto = new OrganizationDTO().setId(1L).setOrganizationName("ООО Хим-Пром");
        when(organizationRepository.findById(anyLong())).thenReturn(Optional.empty());
        var thrown = assertThrows(OrganizationNotExistException.class,
                () -> organizationService.updateOrganizationInfo(dto));
        assertEquals(ORGANIZATION_NOT_EXISTING_ERROR, thrown.getMessage());
    }
}