package ru.trudexpert.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.trudexpert.server.entity.Organization;

import java.util.List;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    boolean existsByName(String name);

    @Query(value = "select * from organizations o where lower(o.name) like lower(:name) order by o.id", nativeQuery = true)
    List<Organization> findAllByName(@Param("name") String companyName);

    @Query(value = "select * from organizations o order by o.id", nativeQuery = true)
    List<Organization> findAllOrganizations();

    @Query(value = "select * from organizations o where o.id in (select ol.organization_id from organization_listener ol where ol.listener_id = :listener_id)", nativeQuery = true)
    List<Organization> findAllListenerOrganizations(@Param("listener_id") Long listenerId);
}
