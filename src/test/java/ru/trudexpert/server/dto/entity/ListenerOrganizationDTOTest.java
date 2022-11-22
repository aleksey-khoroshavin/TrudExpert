package ru.trudexpert.server.dto.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.trudexpert.server.entity.ListenerOrganization;
import ru.trudexpert.server.entity.Organization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListenerOrganizationDTOTest {
    private static final String TEST_POST = "Сварщик";
    private static final String TEST_COMPANY_NAME = "ООО Хим-Пром";

    @Mock
    private Organization organization;

    @InjectMocks
    private ListenerOrganization entity;

    @Test
    void getFromEntityTest() {
        when(organization.getId()).thenReturn(1L);
        when(organization.getName()).thenReturn(TEST_COMPANY_NAME);
        entity.setPost(TEST_POST);

        var dto = ListenerOrganizationDTO.getFromEntity(entity);
        assertEquals(TEST_POST, dto.getPost());
        assertEquals(1, dto.getOrganizationId());
        assertEquals(TEST_COMPANY_NAME, dto.getOrganizationName());
    }

    @Test
    void getFromNullEntityTest() {
        assertNull(ListenerOrganizationDTO.getFromEntity(null));
    }
}