package ru.trudexpert.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "listeners")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Listener {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "surname", precision = 100)
    private String surname;

    @Column(name = "name", precision = 80)
    private String name;

    @Column(name = "patronymic", precision = 150)
    private String patronymic;

    @Column(name = "date_of_birth")
    private Instant dateOfBirth;

    @Column(name = "snils", precision = 50)
    private String snils;

    @Column(name = "gender", precision = 50)
    private String gender;

    @Column(name = "phone_number", precision = 100)
    private String phoneNumber;

    @Column(name = "citizenship_code")
    private Long citizenshipCode;

    @Column(name = "driver_license", precision = 50)
    private String driverLicense;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "passport_series", precision = 50)
    private String passportSeries;

    @Column(name = "passport_number", precision = 70)
    private String passportNumber;

    @Column(name = "passport_issued_by", columnDefinition = "TEXT")
    private String passportIssuedBy;

    @Column(name = "education", columnDefinition = "TEXT")
    private String education;
}
