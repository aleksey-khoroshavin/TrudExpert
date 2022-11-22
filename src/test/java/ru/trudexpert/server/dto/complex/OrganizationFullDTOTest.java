package ru.trudexpert.server.dto.complex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.trudexpert.server.dto.entity.OrganizationAgentDTO;
import ru.trudexpert.server.dto.entity.OrganizationDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrganizationFullDTOTest {

    private static OrganizationFullDTO dto;

    @BeforeEach
    void initDto() {
        dto = new OrganizationFullDTO();
    }

    @Test
    @DisplayName("Проверка данных организации в структуре полной информации ЮЛ")
    void organizationDTOTest() {
        var organizationDTO = new OrganizationDTO()
                .setId(Long.parseLong("1"))
                .setOrganizationName("TEST")
                .setBik("123456789")
                .setEmail("test@mail.com")
                .setFactAddress("Asia, India, San-Andreas, 45");

        dto.setOrganizationDTO(organizationDTO);

        var actualDto = dto.getOrganizationDTO();
        assertEquals(1, actualDto.getId());
        assertEquals("TEST", actualDto.getOrganizationName());
        assertEquals("123456789", actualDto.getBik());
        assertEquals("test@mail.com", actualDto.getEmail());
        assertEquals("Asia, India, San-Andreas, 45", actualDto.getFactAddress());
    }

    @Test
    @DisplayName("Проверка данных контрагента в структуре полной информации ЮЛ")
    void organizationAgentDTOTest() {
        var agentDto = new OrganizationAgentDTO()
                .setId(Long.parseLong("1"))
                .setName("Иван")
                .setSurname("Иванов")
                .setPatronymic("Иванович")
                .setDocument("Приказ об утверждении начальника отдела");

        dto.setOrganizationAgentDTO(agentDto);

        var actualDto = dto.getOrganizationAgentDTO();
        assertEquals(1, actualDto.getId());
        assertEquals("Иван", actualDto.getName());
        assertEquals("Иванов", actualDto.getSurname());
        assertEquals("Иванович", actualDto.getPatronymic());
        assertEquals("Приказ об утверждении начальника отдела", actualDto.getDocument());
    }
}