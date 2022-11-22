package ru.trudexpert.server.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.trudexpert.server.dto.entity.ListenerDTO;
import ru.trudexpert.server.entity.Listener;
import ru.trudexpert.server.exception.ListenerAlreadyRegisteredException;
import ru.trudexpert.server.exception.ListenerNotFoundException;
import ru.trudexpert.server.exception.SnilsAlreadyRegisteredException;
import ru.trudexpert.server.repository.ListenerRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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
class ListenerServiceTest {
    private static final String LISTENER_ALREADY_EXISTING_ERROR = "Такой слушатель уже зарегистрирован в системе";
    private static final String LISTENER_NOT_FOUND_ERROR = "Слушатель не найден в системе";
    private static final String SNILS_REGISTERED_ERROR = "Такой СНИЛС уже зарегистрирован в системе";
    private static final String PATTERN_FORMAT = "yyyy-MM-dd";
    private static DateTimeFormatter formatter;

    @Mock
    private ListenerRepository listenerRepository;

    @InjectMocks
    private ListenerService listenerService;

    @BeforeAll
    static void initBeforeAll() {
        formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT).withZone(ZoneId.systemDefault());
    }

    @Test
    void saveListenerTest() {
        var dto = new ListenerDTO()
                .setId(1L)
                .setSurname("Иванов")
                .setName("Иван")
                .setPatronymic("Ивнонвич")
                .setDateOfBirth("1976-02-20")
                .setSnils("192-334-343-53");

        when(listenerRepository.existsByFullName(anyString(), anyString(), anyString())).thenReturn(false);
        when(listenerRepository.existsBySnils(anyString())).thenReturn(false);
        when(listenerRepository.save(notNull())).thenReturn(Listener.getFromDTO(dto));

        assertDoesNotThrow(() -> listenerService.saveListener(dto));
    }

    @Test
    void saveListenerWithNoPatronymic() {
        var dto = new ListenerDTO()
                .setId(1L)
                .setSurname("Иванов")
                .setName("Иван")
                .setDateOfBirth("1976-02-20")
                .setSnils("192-334-343-53");

        when(listenerRepository.existsBySurnameAndName(anyString(), anyString())).thenReturn(false);
        when(listenerRepository.existsBySnils(anyString())).thenReturn(false);
        when(listenerRepository.save(notNull())).thenReturn(Listener.getFromDTO(dto));

        assertDoesNotThrow(() -> listenerService.saveListener(dto));
    }

    @Test
    void saveListenerWithSameFullNameTest() {
        var dto = new ListenerDTO()
                .setId(1L)
                .setSurname("Иванов")
                .setName("Иван")
                .setPatronymic("Ивнонвич")
                .setDateOfBirth("1976-02-20")
                .setSnils("192-334-343-53");

        when(listenerRepository.existsByFullName(anyString(), anyString(), anyString())).thenReturn(true);
        when(listenerRepository.existsBySnils(anyString())).thenReturn(false);
        when(listenerRepository.existsByDateOfBirth(notNull())).thenReturn(false);
        when(listenerRepository.save(notNull())).thenReturn(Listener.getFromDTO(dto));

        assertDoesNotThrow(() -> listenerService.saveListener(dto));
    }

    @Test
    void saveAlreadyRegisteredListenerTest() {
        var dto = new ListenerDTO()
                .setId(1L)
                .setSurname("Иванов")
                .setName("Иван")
                .setPatronymic("Ивнонвич")
                .setDateOfBirth("1976-02-20")
                .setSnils("192-334-343-53");

        when(listenerRepository.existsByFullName(anyString(), anyString(), anyString())).thenReturn(true);
        when(listenerRepository.existsBySnils(anyString())).thenReturn(false);
        when(listenerRepository.existsByDateOfBirth(notNull())).thenReturn(true);
        var thrown = assertThrows(ListenerAlreadyRegisteredException.class,
                () -> listenerService.saveListener(dto));
        assertEquals(LISTENER_ALREADY_EXISTING_ERROR, thrown.getMessage());
    }

    @Test
    void updateListenerTest() {
        var dto = new ListenerDTO()
                .setId(1L)
                .setSurname("Иванов")
                .setName("Андрей")
                .setDateOfBirth("1995-06-24");

        when(listenerRepository.findById(1L)).thenReturn(Optional.of(new Listener().setId(1L).setName("Иван")));
        when(listenerRepository.save(notNull())).thenReturn(Listener.getFromDTO(dto));
        assertDoesNotThrow(() -> listenerService.updateListener(dto));
    }

    @Test
    void updateNotExistingListener() {
        var dto = new ListenerDTO().setId(1L);
        when(listenerRepository.findById(anyLong())).thenReturn(Optional.empty());
        var thrown = assertThrows(ListenerNotFoundException.class,
                () -> listenerService.updateListener(dto));
        assertEquals(LISTENER_NOT_FOUND_ERROR, thrown.getMessage());
    }

    @Test
    void updateWithIncorrectSnils() {
        var dto = new ListenerDTO().setId(1L).setSnils("198-568-747-86");
        when(listenerRepository.findById(anyLong())).thenReturn(Optional.of(new Listener().setId(1L)));
        when(listenerRepository.existsBySnils("198-568-747-86")).thenReturn(true);
        var thrown = assertThrows(SnilsAlreadyRegisteredException.class,
                () -> listenerService.updateListener(dto));
        assertEquals(SNILS_REGISTERED_ERROR, thrown.getMessage());
    }

    @Test
    void getListenersTest() {
        var listener1 = new Listener()
                .setId(1L)
                .setSurname("Иванов")
                .setName("Иван")
                .setPatronymic("Ивнонвич")
                .setDateOfBirth(LocalDate.parse("1976-03-12", formatter).atStartOfDay().toInstant(ZoneOffset.UTC))
                .setSnils("192-334-343-53");
        var listener2 = new Listener()
                .setId(2L)
                .setSurname("Иванов")
                .setName("Андрей")
                .setPatronymic("Владимирович")
                .setDateOfBirth(LocalDate.parse("1995-11-06", formatter).atStartOfDay().toInstant(ZoneOffset.UTC))
                .setSnils("182-568-457-89");
        var listener3 = new Listener()
                .setId(3L)
                .setSurname("Петров")
                .setName("Дмитрий")
                .setPatronymic("Михайлович")
                .setDateOfBirth(LocalDate.parse("1976-03-12", formatter).atStartOfDay().toInstant(ZoneOffset.UTC))
                .setSnils("192-334-343-00");

        when(listenerRepository.findAllBySurname(anyString())).thenReturn(List.of(listener1, listener2));
        when(listenerRepository.findAllListeners()).thenReturn(List.of(listener1, listener2, listener3));

        var resultAll = listenerService.getListeners();
        assertNotNull(resultAll);
        assertEquals(3, resultAll.size());

        var resultBySurname = listenerService.getListenersBySurname("Иванов");
        assertNotNull(resultBySurname);
        assertEquals(2, resultBySurname.size());
    }

    @Test
    void getListenerByIdTest() throws ListenerNotFoundException {
        var listener = new Listener()
                .setId(1L)
                .setSurname("Иванов")
                .setName("Иван")
                .setPatronymic("Ивнонвич")
                .setDateOfBirth(LocalDate.parse("1976-03-12", formatter).atStartOfDay().toInstant(ZoneOffset.UTC))
                .setSnils("192-334-343-53");

        when(listenerRepository.findById(anyLong())).thenReturn(Optional.of(listener));
        var result = listenerService.getListenerById(1L);
        assertEquals("Иванов", result.getSurname());
        assertEquals("Иван", result.getName());
        assertEquals("1976-03-12", result.getDateOfBirth());
    }

    @Test
    void getNotExistingListenerByIdTest() {
        when(listenerRepository.findById(anyLong())).thenReturn(Optional.empty());
        var thrown = assertThrows(ListenerNotFoundException.class,
                () -> listenerService.getListenerById(1L));
        assertEquals(LISTENER_NOT_FOUND_ERROR, thrown.getMessage());
    }

    @Test
    void getListenerNameTest() throws ListenerNotFoundException {
        var listener = new Listener()
                .setId(1L)
                .setSurname("Иванов")
                .setName("Иван")
                .setPatronymic("Иванович");

        when(listenerRepository.findById(anyLong())).thenReturn(Optional.of(listener));
        var result = listenerService.getListenerName(1L);
        assertEquals("Иванов Иван Иванович", result);
    }

    @Test
    void getNotExistingListenerNameTest() {
        when(listenerRepository.findById(anyLong())).thenReturn(Optional.empty());
        var thrown = assertThrows(ListenerNotFoundException.class,
                () -> listenerService.getListenerName(1L));
        assertEquals(LISTENER_NOT_FOUND_ERROR, thrown.getMessage());
    }
}