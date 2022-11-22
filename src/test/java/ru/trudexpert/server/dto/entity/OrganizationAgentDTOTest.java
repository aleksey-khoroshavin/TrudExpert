package ru.trudexpert.server.dto.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.trudexpert.server.entity.OrganizationAgent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrganizationAgentDTOTest {
    private static final String TEST_NAME = "Иван";
    private static final String TEST_SURNAME = "Иванов";
    private static final String TEST_PATRONYMIC = "Иванович";
    private static final String TEST_DOCUMENT = "Приказ о назначении заместителем исполнителнього директора";
    private static final String TEST_POST = "Заместитель директора производственного цеха";

    @Mock
    private OrganizationAgent entity;

    @Test
    void getFromEntityTest() {
        when(entity.getId()).thenReturn(1L);
        when(entity.getSurname()).thenReturn(TEST_SURNAME);
        when(entity.getName()).thenReturn(TEST_NAME);
        when(entity.getPatronymic()).thenReturn(TEST_PATRONYMIC);
        when(entity.getDocument()).thenReturn(TEST_DOCUMENT);
        when(entity.getPost()).thenReturn(TEST_POST);

        var dto = OrganizationAgentDTO.getFromEntity(entity);

        assertEquals(1L, dto.getId());
        assertEquals(TEST_NAME, dto.getName());
        assertEquals(TEST_SURNAME, dto.getSurname());
        assertEquals(TEST_PATRONYMIC, dto.getPatronymic());
        assertEquals(TEST_POST, dto.getPost());
        assertEquals(TEST_DOCUMENT, dto.getDocument());
    }

    @Test
    void getFromNullEntityTest() {
        assertNull(OrganizationAgentDTO.getFromEntity(null));
    }
}