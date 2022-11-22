package ru.trudexpert.server.dto.shortinfo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.trudexpert.server.entity.Organization;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class OrganizationShortInfoDTOTest {

    private static final String TEST_COMPANY_NAME = "ООО Хим-Пром";

    @Mock
    private Organization entity;

    @Test
    void getFromEntityTest() {
        when(entity.getId()).thenReturn(1L);
        when(entity.getName()).thenReturn(TEST_COMPANY_NAME);

        var dto = OrganizationShortInfoDTO.getFromEntity(entity);

        assertEquals(1L, dto.getId());
        assertEquals(TEST_COMPANY_NAME, dto.getName());
        assertNull(OrganizationShortInfoDTO.getFromEntity(null));
    }
}