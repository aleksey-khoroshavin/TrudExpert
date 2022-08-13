package ru.trudexpert.server.dto;

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
    private static final String PATTERN_FORMAT = "yyyy-MM-dd";

    private Long id;

    private String surname;

    private String name;

    private String patronymic;

    private String dateOfBirth;

    private String snils;

    private String gender;

    private String phoneNumber;

    private Long citizenshipCode;

    private String driverLicense;

    private String address;

    private String passportSeries;

    private String passportNumber;

    private String passportIssuedBy;

    private String passportIssuedAt;

    private String educationType;

    private String educationDocument;

    private String educationDocumentIssuedAt;

    public static ListenerDTO getFromEntity(Listener listener){
        if(listener == null){
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                .withZone(ZoneId.systemDefault());

        return new ListenerDTO()
                .setId(listener.getId())
                .setSurname(listener.getSurname())
                .setName(listener.getName())
                .setPatronymic(listener.getPatronymic())
                .setDateOfBirth(formatter.format(listener.getDateOfBirth()))
                .setSnils(listener.getSnils())
                .setGender(listener.getGender())
                .setPhoneNumber(listener.getPhoneNumber().substring(3))
                .setCitizenshipCode(listener.getCitizenshipCode())
                .setDriverLicense(listener.getDriverLicense())
                .setAddress(listener.getAddress())
                .setPassportSeries(listener.getPassportSeries())
                .setPassportNumber(listener.getPassportNumber())
                .setPassportIssuedBy(listener.getPassportIssuedBy())
                .setPassportIssuedAt(listener.getPassportIssuedAt() != null ?
                        formatter.format(listener.getPassportIssuedAt()) : null)
                .setEducationType(listener.getEducationType())
                .setEducationDocument(listener.getEducationDocument())
                .setEducationDocumentIssuedAt(listener.getEducationDocumentIssuedAt() != null ?
                        formatter.format(listener.getEducationDocumentIssuedAt()) : null);
    }
}
