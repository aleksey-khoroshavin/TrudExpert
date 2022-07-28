package ru.trudexpert.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "passports")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Passport {
    @Id
    @Column(name = "listener_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "listener_id")
    private Listener listener;

    @Column(name = "series")
    private String series;

    @Column(name = "number")
    private String number;

    @Column(name = "issued_by", nullable = false, columnDefinition = "TEXT")
    private String issuedBy;
}
