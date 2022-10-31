package ru.trudexpert.server.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.trudexpert.server.entity.Listener;
import ru.trudexpert.server.entity.ListenerOrganization;
import ru.trudexpert.server.entity.Organization;
import ru.trudexpert.server.exception.ListenerNotFoundException;
import ru.trudexpert.server.exception.OrganizationNotExistException;
import ru.trudexpert.server.repository.ListenerOrganizationRepository;
import ru.trudexpert.server.repository.ListenerRepository;
import ru.trudexpert.server.repository.OrganizationRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListenerOrganizationServiceTest {
    private static final String LISTENER_NOT_FOUND_ERROR = "Слушатель не найден в системе";
    private static final String ORGANIZATION_NOT_FOUND_ERROR = "Данная организация не найдена";

    @Mock
    private ListenerRepository listenerRepository;

    @Mock
    private OrganizationRepository organizationRepository;

    @Mock
    private ListenerOrganizationRepository listenerOrganizationRepository;

    @InjectMocks
    private ListenerOrganizationService listenerOrganizationService;

    @Test
    void getOrganizationsOfListenerTest() throws ListenerNotFoundException {
        var listener = new Listener()
                .setId(1L)
                .setSurname("Иванов");

        var organization1 = new Organization()
                .setId(1L)
                .setName("ОАО Хим-Пром");

        var organization2 = new Organization()
                .setId(2L)
                .setName("Бетон-НСК");

        var listenerOrganization1 = new ListenerOrganization()
                .setListener(listener)
                .setOrganization(organization1);

        var listenerOrganization2 = new ListenerOrganization()
                .setListener(listener)
                .setOrganization(organization2);

        listener.setOrganizations(Set.of(listenerOrganization1, listenerOrganization2));

        when(listenerRepository.findById(anyLong())).thenReturn(Optional.of(listener));

        var result = listenerOrganizationService.getOrganizationsOfListener(1L);
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void getOrganizationsOfNotExistingListenerTest() {
        when(listenerRepository.findById(anyLong())).thenReturn(Optional.empty());

        var thrown = assertThrows(ListenerNotFoundException.class,
                () -> listenerOrganizationService.getOrganizationsOfListener(1L));
        assertEquals(LISTENER_NOT_FOUND_ERROR, thrown.getMessage());
    }

    @Test
    void addToOrganizationTest() {
        var organization = new Organization().setId(1L).setListeners(new HashSet<>());
        var listener = new Listener().setId(1L).setOrganizations(new HashSet<>());

        when(organizationRepository.findById(1L)).thenReturn(Optional.of(organization));
        when(listenerRepository.findById(1L)).thenReturn(Optional.of(listener));
        when(organizationRepository.save(organization)).thenReturn(organization);
        when(listenerRepository.save(listener)).thenReturn(listener);

        assertDoesNotThrow(() -> listenerOrganizationService.addToOrganization(1L, 1L, "post"));
    }

    @Test
    void addToNotExistingOrganizationTest() {
        when(organizationRepository.findById(anyLong())).thenReturn(Optional.empty());

        var thrown = assertThrows(OrganizationNotExistException.class,
                () -> listenerOrganizationService.addToOrganization(1L, 1L, "post"));
        assertEquals(ORGANIZATION_NOT_FOUND_ERROR, thrown.getMessage());
    }

    @Test
    void addToOrganizationNotExistingListener() {
        var organization = new Organization();
        when(organizationRepository.findById(anyLong())).thenReturn(Optional.of(organization));
        when(listenerRepository.findById(anyLong())).thenReturn(Optional.empty());

        var thrown = assertThrows(ListenerNotFoundException.class,
                () -> listenerOrganizationService.addToOrganization(1L, 1L, "post"));
        assertEquals(LISTENER_NOT_FOUND_ERROR, thrown.getMessage());
    }

    @Test
    void deleteFromOrganizationTest() {
        var listener = new Listener().setId(1L);
        var organization = new Organization().setId(1L);
        var listenerOrganizationRecord = new ListenerOrganization()
                .setListener(listener)
                .setOrganization(organization);

        var recordsList = new HashSet<ListenerOrganization>();
        recordsList.add(listenerOrganizationRecord);

        organization.setListeners(recordsList);
        listener.setOrganizations(recordsList);

        when(organizationRepository.findById(1L)).thenReturn(Optional.of(organization));
        when(listenerRepository.findById(1L)).thenReturn(Optional.of(listener));
        when(organizationRepository.save(organization)).thenReturn(organization);
        when(listenerRepository.save(listener)).thenReturn(listener);
        doNothing().when(listenerOrganizationRepository).deleteById(notNull());

        assertDoesNotThrow(() -> listenerOrganizationService.deleteFromOrganization(1L, 1L));
    }

    @Test
    void deleteFromNotExistingOrganization() {
        when(organizationRepository.findById(anyLong())).thenReturn(Optional.empty());

        var thrown = assertThrows(OrganizationNotExistException.class,
                () -> listenerOrganizationService.deleteFromOrganization(1L, 1L));
        assertEquals(ORGANIZATION_NOT_FOUND_ERROR, thrown.getMessage());
    }

    @Test
    void deleteFromOrganizationNotExistingUser() {
        var organization = new Organization();
        when(organizationRepository.findById(anyLong())).thenReturn(Optional.of(organization));
        when(listenerRepository.findById(anyLong())).thenReturn(Optional.empty());

        var thrown = assertThrows(ListenerNotFoundException.class,
                () -> listenerOrganizationService.deleteFromOrganization(1L, 1L));
        assertEquals(LISTENER_NOT_FOUND_ERROR, thrown.getMessage());
    }
}