package ru.trudexpert.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.trudexpert.server.entity.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByDescription(String description);

    @Query(value = "select * from courses c order by c.description", nativeQuery = true)
    List<Course> findAllCourses();

    @Query(value = "select * from courses c where lower(c.description) like lower(?1) order by c.description", nativeQuery = true)
    List<Course> findAllByDescription(String desc);
}
