package ru.trudexpert.server.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.trudexpert.server.entity.Course;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class CourseDTO {
    private Long id;

    private String description;

    private Long studyingTime;

    private String cost;

    public static CourseDTO getFromEntity(Course course){
        if(course == null){
            return null;
        }

        return new CourseDTO()
                .setId(course.getId())
                .setDescription(course.getDescription())
                .setStudyingTime(course.getStudyingTime())
                .setCost(course.getCost().toString());
    }
}
