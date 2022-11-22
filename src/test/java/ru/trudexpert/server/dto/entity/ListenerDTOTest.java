package ru.trudexpert.server.dto.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.trudexpert.server.entity.Listener;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListenerDTOTest {
    private static final String PATTERN_FORMAT = "yyyy-MM-dd";
    private static final String TEST_DATE = "1976-03-20";
    private static final String TEST_NAME = "Иван";
    private static final String TEST_SURNAME = "Иванов";
    private static final String TEST_PATRONYMIC = "Иванович";
    private static final String TEST_SNILS = "193-234-022-02";
    private static final String TEST_PHONE_NUMBER = "+7-123-456-78-90";
    private static final String TEST_PASSPORT_SERIES = "0102";
    private static final String TEST_PASSPORT_NUMBER = "999000";

    private Listener listener;
    private DateTimeFormatter formatter;

    @BeforeEach
     void initEntity() {
        listener = new Listener();
        listener.setId(Long.parseLong("1"));
        formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT).withZone(ZoneId.systemDefault());
        listener.setDateOfBirth(LocalDate.parse(TEST_DATE, formatter).atStartOfDay().toInstant(ZoneOffset.UTC));
    }

    @Test
    void getFromEntity() {
        listener.setSurname(TEST_SURNAME);
        listener.setName(TEST_NAME);
        listener.setPatronymic(TEST_PATRONYMIC);
        listener.setSnils(TEST_SNILS);
        listener.setPassportSeries(TEST_PASSPORT_SERIES);
        listener.setPassportNumber(TEST_PASSPORT_NUMBER);

        var dto = ListenerDTO.getFromEntity(listener);
        assertEquals(1, dto.getId());
        assertEquals(TEST_SURNAME, dto.getSurname());
        assertEquals(TEST_NAME, dto.getName());
        assertEquals(TEST_PATRONYMIC, dto.getPatronymic());
        assertEquals(TEST_DATE, dto.getDateOfBirth());
        assertEquals(TEST_PASSPORT_SERIES, dto.getPassportSeries());
        assertEquals(TEST_PASSPORT_NUMBER, dto.getPassportNumber());

        assertNull(dto.getPhoneNumber());
        assertNull(dto.getEducationDocumentIssuedAt());
        assertNull(dto.getPassportIssuedAt());
    }

    @Test
    void getFromNullEntity() {
        assertNull(ListenerDTO.getFromEntity(null));
    }

    @Test
    void getFromEntityWithPhoneNumber() {
        listener.setPhoneNumber(TEST_PHONE_NUMBER);
        var dto = ListenerDTO.getFromEntity(listener);
        assertTrue(TEST_PHONE_NUMBER.contains(dto.getPhoneNumber()));
        assertEquals(TEST_PHONE_NUMBER.substring(3), dto.getPhoneNumber());
    }

    @Test
    void getFromEntityWithDateOfPassportIssue() {
        listener.setPassportIssuedAt(LocalDate.parse(TEST_DATE, formatter).atStartOfDay().toInstant(ZoneOffset.UTC));
        var dto = ListenerDTO.getFromEntity(listener);
        assertEquals(TEST_DATE, dto.getPassportIssuedAt());
    }

    @Test
    void getFromEntityWithDateOfGraduation() {
        listener.setEducationDocumentIssuedAt(LocalDate.parse(TEST_DATE, formatter).atStartOfDay().toInstant(ZoneOffset.UTC));
        var dto = ListenerDTO.getFromEntity(listener);
        assertEquals(TEST_DATE, dto.getEducationDocumentIssuedAt());
    }
}