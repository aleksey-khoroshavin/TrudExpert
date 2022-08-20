package ru.trudexpert.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.trudexpert.server.entity.Organization;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    boolean existsByName(String name);

    Optional<Organization> findByName(String name);

    @Query(value = "select * from organizations o where o.name like ?1", nativeQuery = true)
    List<Organization> findAllByName(String companyName);
}
