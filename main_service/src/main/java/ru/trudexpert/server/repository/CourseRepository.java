package ru.trudexpert.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.trudexpert.server.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
