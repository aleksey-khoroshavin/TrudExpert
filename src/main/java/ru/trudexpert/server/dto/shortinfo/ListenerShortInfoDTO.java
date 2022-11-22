package ru.trudexpert.server.dto.shortinfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.trudexpert.server.entity.Listener;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ListenerShortInfoDTO {
    private static final String PATTERN_FORMAT = "dd.MM.yyyy";

    private Long id;

    private String surname;

    private String name;

    private String patronymic;

    private String dateOfBirth;

    private String snils;

    public static ListenerShortInfoDTO getFromEntity(Listener listener) {
        if (listener == null) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                .withZone(ZoneId.systemDefault());

        return new ListenerShortInfoDTO()
                .setId(listener.getId())
                .setSurname(listener.getSurname())
                .setName(listener.getName())
                .setPatronymic(listener.getPatronymic())
                .setDateOfBirth(formatter.format(listener.getDateOfBirth()))
                .setSnils(listener.getSnils());
    }
}
