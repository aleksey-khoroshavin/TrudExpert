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
public class ListenerDTO {
    private static final String PATTERN_FORMAT = "dd.MM.yyyy";

    @NotNull
    private String surname;

    @NotNull
    private String name;

    @NotNull
    private String patronymic;

    @NotNull
    private String dateOfBirth;

    @NotNull
    private String snils;

    @NotNull
    private String gender;

    @NotNull
    private String phoneNumber;

    @NotNull
    private Long citizenshipCode;

    @NotNull
    private String driverLicense;

    @NotNull
    private String address;

    @NotNull
    private String passportSeries;

    @NotNull
    private String passportNumber;

    @NotNull
    private String passportIssuedBy;

    @NotNull
    private String passportIssuedAt;

    @NotNull
    private String educationType;

    @NotNull
    private String educationDocument;

    @NotNull
    private String educationDocumentIssuedAt;

    public static ListenerDTO getFromEntity(Listener listener){
        if(listener == null){
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                .withZone(ZoneId.systemDefault());

        return new ListenerDTO()
                .setSurname(listener.getSurname())
                .setName(listener.getName())
                .setPatronymic(listener.getPatronymic())
                .setDateOfBirth(formatter.format(listener.getDateOfBirth()))
                .setSnils(listener.getSnils())
                .setGender(listener.getGender())
                .setPhoneNumber(listener.getPhoneNumber())
                .setCitizenshipCode(listener.getCitizenshipCode())
                .setDriverLicense(listener.getDriverLicense())
                .setAddress(listener.getAddress())
                .setPassportSeries(listener.getPassportSeries())
                .setPassportNumber(listener.getPassportNumber())
                .setPassportIssuedBy(listener.getPassportIssuedBy())
                .setPassportIssuedAt(formatter.format(listener.getPassportIssuedAt()))
                .setEducationType(listener.getEducationType())
                .setEducationDocument(listener.getEducationDocument())
                .setEducationDocumentIssuedAt(formatter.format(listener.getEducationDocumentIssuedAt()));
    }
}
