package ru.trudexpert.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.trudexpert.server.entity.Passport;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Long> {

}
