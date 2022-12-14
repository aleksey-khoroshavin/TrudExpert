package ru.trudexpert.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.trudexpert.server.entity.Listener;

import java.time.Instant;
import java.util.List;

@Repository
public interface ListenerRepository extends JpaRepository<Listener, Long> {
    boolean existsBySnils(String snils);

    @Query(value = "select * from listeners l where LOWER(l.surname) like LOWER(?1) order by l.name", nativeQuery = true)
    List<Listener> findAllBySurname(String surname);

    @Query(value = "select * from listeners l order by l.surname", nativeQuery = true)
    List<Listener> findAllListeners();

    @Query("select case when count(l) > 0 then true else false end from Listener l where lower(l.surname) like lower(:surname) " +
            "and lower(l.name) like lower(:name) and lower(l.patronymic) like lower(:patronymic)")
    boolean existsByFullName(
            @Param("surname") String surname,
            @Param("name") String name,
            @Param("patronymic") String patronymic);

    boolean existsBySurnameAndName(String surname, String name);

    boolean existsByDateOfBirth(Instant date);

}
