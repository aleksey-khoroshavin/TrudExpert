package ru.trudexpert.server.dto.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.trudexpert.server.entity.Organization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrganizationDTOTest {
    private static final String TEST_COMPANY_NAME = "ООО Бетон-НСК";
    private static final String TEST_BIK = "044525225";
    private static final String TEST_PHONE = "+7-123-456-78-90";
    private static final String TEST_ADDRESS = "Новосибирск, ул. Кирова, д. 56, офис. 800";
    private static final String TEST_EMAIL = "testCompany@gmail.com";
    private static final String TEST_INN = "7727563778";

    @Mock
    private Organization entity;

    @Test
    void getFromEntityTest() {
        when(entity.getId()).thenReturn(1L);
        when(entity.getName()).thenReturn(TEST_COMPANY_NAME);
        when(entity.getBik()).thenReturn(TEST_BIK);
        when(entity.getPhone()).thenReturn(TEST_PHONE);
        when(entity.getFactAddress()).thenReturn(TEST_ADDRESS);
        when(entity.getLawAddress()).thenReturn(TEST_ADDRESS);
        when(entity.getEmail()).thenReturn(TEST_EMAIL);
        when(entity.getInn()).thenReturn(TEST_INN);

        var dto = OrganizationDTO.getFromEntity(entity);

        assertEquals(1L, dto.getId());
        assertEquals(TEST_COMPANY_NAME, dto.getOrganizationName());
        assertEquals(TEST_BIK, dto.getBik());
        assertEquals(TEST_PHONE, dto.getPhone());
        assertEquals(TEST_ADDRESS, dto.getFactAddress());
        assertEquals(TEST_ADDRESS, dto.getLawAddress());
        assertEquals(TEST_EMAIL, dto.getEmail());
        assertEquals(TEST_INN, dto.getInn());
    }

    @Test
    void getFromNullEntityTest() {
        assertNull(OrganizationDTO.getFromEntity(null));
    }
}