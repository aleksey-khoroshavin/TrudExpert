package ru.trudexpert.server.dto;

import com.sun.istack.NotNull;
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

    @NotNull
    private Long id;

    @NotNull
    private String surname;

    @NotNull
    private String name;

    @NotNull
    private String patronymic;

    @NotNull
    private String dateOfBirth;

    public static ListenerShortInfoDTO getFromEntity(Listener listener){
        if(listener == null){
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                .withZone(ZoneId.systemDefault());

        return new ListenerShortInfoDTO()
                .setId(listener.getId())
                .setSurname(listener.getSurname())
                .setName(listener.getName())
                .setPatronymic(listener.getPatronymic())
                .setDateOfBirth(formatter.format(listener.getDateOfBirth()));
    }
}
