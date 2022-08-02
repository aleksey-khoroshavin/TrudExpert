package ru.trudexpert.server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.trudexpert.server.entity.Listener;

import java.util.List;

@Repository
public interface ListenerRepository extends JpaRepository<Listener, Long> {
    boolean existsBySnils(String snils);

    List<Listener> findAllBySurname(String surname);
}
