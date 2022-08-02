package ru.trudexpert.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.trudexpert.server.dto.ListenerDTO;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "listeners")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Listener {
    private static final String PATTERN_FORMAT = "yyyy-MM-dd";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "surname", precision = 100)
    private String surname;

    @Column(name = "name", precision = 80)
    private String name;

    @Column(name = "patronymic", precision = 150)
    private String patronymic;

    @Column(name = "date_of_birth", columnDefinition = "date")
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

    @Column(name = "passport_issued_at", columnDefinition = "date")
    private Instant passportIssuedAt;

    @Column(name = "education_type", columnDefinition = "text")
    private String educationType;

    @Column(name = "education_document", columnDefinition = "TEXT")
    private String educationDocument;

    @Column(name = "education_document_issued_at", columnDefinition = "date")
    private Instant educationDocumentIssuedAt;

    public static Listener getFromDTO(ListenerDTO dto){
        if(dto == null){
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                .withZone(ZoneId.systemDefault());

        return new Listener()
                .setSurname(dto.getSurname())
                .setName(dto.getName())
                .setPatronymic(dto.getPatronymic())
                .setDateOfBirth(dto.getDateOfBirth() != null && !dto.getDateOfBirth().isEmpty() ?
                        LocalDate.parse(dto.getDateOfBirth(), formatter).atStartOfDay().toInstant(ZoneOffset.UTC) :
                        null)
                .setSnils(dto.getSnils())
                .setGender(dto.getGender())
                .setPhoneNumber(dto.getPhoneNumber())
                .setCitizenshipCode(dto.getCitizenshipCode())
                .setDriverLicense(dto.getDriverLicense())
                .setAddress(dto.getAddress())
                .setPassportSeries(dto.getPassportSeries())
                .setPassportNumber(dto.getPassportNumber())
                .setPassportIssuedBy(dto.getPassportIssuedBy())
                .setPassportIssuedAt(dto.getPassportIssuedAt() != null && !dto.getPassportIssuedAt().isEmpty() ?
                        LocalDate.parse(dto.getPassportIssuedAt(), formatter).atStartOfDay().toInstant(ZoneOffset.UTC) :
                        null)
                .setEducationType(dto.getEducationType())
                .setEducationDocument(dto.getEducationDocument())
                .setEducationDocumentIssuedAt(dto.getEducationDocumentIssuedAt() != null && !dto.getEducationDocumentIssuedAt().isEmpty()?
                        LocalDate.parse(dto.getEducationDocumentIssuedAt(), formatter).atStartOfDay().toInstant(ZoneOffset.UTC) :
                        null);
    }
}
