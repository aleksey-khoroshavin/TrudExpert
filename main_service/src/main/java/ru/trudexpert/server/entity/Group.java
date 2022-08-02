package ru.trudexpert.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "groups")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false)
    private Long number;

    @Column(name = "study_start_date", nullable = false)
    private Instant studyStartDate;

    @Column(name = "study_end_date", nullable = false)
    private Instant studyEndDate;

}
