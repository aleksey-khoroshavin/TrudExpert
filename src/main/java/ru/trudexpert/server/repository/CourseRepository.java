package ru.trudexpert.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query(value = "select * from courses c where c.id in (select lc.course_id from listener_courses lc where lc.listener_id = :listener_id)", nativeQuery = true)
    List<Course> findListenerCourses(@Param("listener_id") Long listenerId);

    @Query(value = "select * from courses c where c.id not in (select lc.course_id from listener_courses lc where lc.listener_id = :listener_id)", nativeQuery = true)
    List<Course> findCoursesNotAttachedToListener(@Param("listener_id") Long listenerId);

    @Query(value = "select l.surname || ' ' || l.name || ' ' || l.patronymic from listeners l where id = :listener_id", nativeQuery = true)
    String getListenerName(@Param("listener_id") Long listenerId);

    @Query(value = "select case when count(l) > 0 then true else false end from listeners l where l.id = :listener_id", nativeQuery = true)
    boolean checkIsListenerExists(@Param("listener_id") Long listenerId);

    @Query(value = "select case when count(c) > 0 then true else false end from courses c where c.id = :course_id", nativeQuery = true)
    boolean checkIsCourseExists(@Param("course_id") Long courseId);

    @Query(value = "insert into listener_courses values (?1, ?2)", nativeQuery = true)
    void addListenerToCourse(Long listenerId, Long courseId);
}
