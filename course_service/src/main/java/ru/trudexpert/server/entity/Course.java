package ru.trudexpert.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.trudexpert.server.dto.CourseDTO;

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

    @Column(name = "cost", nullable = false, columnDefinition = "numeric(7,2)")
    private BigDecimal cost;

    public static Course getFromDTO(CourseDTO dto){
        if(dto == null){
            return null;
        }

        return new Course()
                .setDescription(dto.getDescription())
                .setCost(dto.getCost())
                .setStudyingTime(dto.getStudyingTime());
    }

}
