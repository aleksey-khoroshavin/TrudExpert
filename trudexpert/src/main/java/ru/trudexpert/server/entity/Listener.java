package ru.trudexpert.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

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

    @Column(name = "surname", nullable = false, precision = 100)
    private String surname;

    @Column(name = "name", nullable = false, precision = 80)
    private String name;

    @Column(name = "patronymic", nullable = false, precision = 150)
    private String patronymic;

    @Column(name = "date_of_birth", nullable = false)
    private String dateOfBirth;

    @Column(name = "snils", nullable = false, precision = 50)
    private String snils;

    @Column(name = "gender", nullable = false, precision = 50)
    private String gender;

    @Column(name = "phone_number", nullable = false, precision = 100)
    private String phoneNumber;

    @Column(name = "citizenship_code", nullable = false)
    private Long citizenshipCode;

    @Column(name = "driver_license")
    private String driverLicense;

    @OneToOne(mappedBy = "listener", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Passport passport;

    @OneToOne(mappedBy = "listener", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;
}
