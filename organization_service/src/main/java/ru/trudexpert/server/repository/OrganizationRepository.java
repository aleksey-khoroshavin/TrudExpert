package ru.trudexpert.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.trudexpert.server.entity.Organization;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    boolean existsByName(String name);

    Optional<Organization> findByName(String name);
}
