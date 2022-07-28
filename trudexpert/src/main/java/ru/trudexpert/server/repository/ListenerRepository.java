package ru.trudexpert.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.trudexpert.server.entity.Listener;

@Repository
public interface ListenerRepository extends JpaRepository<Listener, Long> {
    boolean existsBySnils(String snils);
}
