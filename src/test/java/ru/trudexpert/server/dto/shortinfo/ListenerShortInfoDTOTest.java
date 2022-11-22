package ru.trudexpert.server.dto.shortinfo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.trudexpert.server.entity.Listener;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListenerShortInfoDTOTest {
    private static final String ENTITY_PATTERN_FORMAT = "yyyy-MM-dd";
    private static final String TEST_NAME = "Иван";
    private static final String TEST_SURNAME = "Иванов";
    private static final String TEST_PATRONYMIC = "Иванович";
    private static final String TEST_SNILS = "193-234-022-02";
    private static final String ENTITY_TEST_DATE = "1976-03-20";
    private static final String DTO_TEST_DATE = "20.03.1976";

    @Mock
    private Listener entity;

    @Test
    void getFromEntityTest() {
        when(entity.getId()).thenReturn(1L);
        when(entity.getSurname()).thenReturn(TEST_SURNAME);
        when(entity.getName()).thenReturn(TEST_NAME);
        when(entity.getPatronymic()).thenReturn(TEST_PATRONYMIC);
        when(entity.getSnils()).thenReturn(TEST_SNILS);
        when(entity.getDateOfBirth()).thenReturn(
                LocalDate.parse(
                                ENTITY_TEST_DATE, DateTimeFormatter
                                        .ofPattern(ENTITY_PATTERN_FORMAT)
                                        .withZone(ZoneId.systemDefault()))
                        .atStartOfDay()
                        .toInstant(ZoneOffset.UTC));

        var dto = ListenerShortInfoDTO.getFromEntity(entity);

        assertEquals(1L, dto.getId());
        assertEquals(TEST_SURNAME, dto.getSurname());
        assertEquals(TEST_NAME, dto.getName());
        assertEquals(TEST_PATRONYMIC, dto.getPatronymic());
        assertEquals(TEST_SNILS, dto.getSnils());
        assertEquals(DTO_TEST_DATE, dto.getDateOfBirth());

        assertNull(ListenerShortInfoDTO.getFromEntity(null));
    }
}