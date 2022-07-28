package ru.trudexpert.server.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ListenerDetails {
    private String surname;
    private String name;
    private String patronymic;
    private String dateOfBirth;
    private String snils;
    private String gender;
    private String phoneNumber;
    private Long citizenshipCode;
    private String driverLicense;
}
