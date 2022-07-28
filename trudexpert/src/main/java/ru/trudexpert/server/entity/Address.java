package ru.trudexpert.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Address {
    @Id
    @Column(name = "listener_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "listener_id")
    private Listener listener;

    @Column(name = "city", nullable = false, precision = 100)
    private String city;

    @Column(name = "square", nullable = false, precision = 100)
    private String square;

    @Column(name = "house_number", nullable = false)
    private Long houseNumber;

    @Column(name = "room_number", nullable = false)
    private Long roomNumber;
}
