package ru.trudexpert.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "courses")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "studying_time", nullable = false)
    private Long studyingTime;

    @Column(name = "cost", nullable = false, columnDefinition = "numeric(5,2)")
    private BigDecimal cost;

}
